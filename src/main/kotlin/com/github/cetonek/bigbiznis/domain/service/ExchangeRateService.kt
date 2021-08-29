package com.github.cetonek.bigbiznis.domain.service

import com.github.cetonek.bigbiznis.application.configuration.BigBiznisConfiguration
import com.github.cetonek.bigbiznis.application.utility.getLogger
import com.github.cetonek.bigbiznis.domain.repository.ExchangeRateRepository
import com.github.cetonek.bigbiznis.integration.cnb.CzechNationalBankClient
import com.github.cetonek.bigbiznis.integration.cnb.ExchangeRateRootDto
import com.github.cetonek.bigbiznis.integration.cnb.toDomain
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

@Service
class ExchangeRateService(
        private val cnbClient: CzechNationalBankClient,
        private val exchangeRepository: ExchangeRateRepository,
        private val config: BigBiznisConfiguration) {

    private val log = getLogger(this::class.java)

    fun synchronizeTodaysExchangeRates() = executeSynchForDates(listOf(LocalDate.now()))

    fun synchronizeAllExchangeRates() {
        executeSynchForDates(exchangeRepository.findAllWeekDaysThatAreMissing(config.exchangeRate.largeSyncStartingDate)
                .map { it.i })
    }

    private fun executeSynchForDates(dates: Collection<LocalDate>) {
        val syncStart = System.currentTimeMillis()
        val count = AtomicInteger(0)
        dates.parallelStream()
                .map {
                    try {
                        Optional.ofNullable(cnbClient.fetchExchangeRateForDay(it).body)
                    } catch (e: Exception) {
                        Optional.empty<ExchangeRateRootDto>()
                    }
                }
                .filter { it.isPresent }
                .map { it.get() }
                .peek { count.incrementAndGet() }
                .map { it.toDomain() }
                .flatMap { it.stream() }
                .forEach { exchangeRepository.updateOrCreateEntity(it) }
                .also {
                    val executionTime = System.currentTimeMillis() - syncStart
                    log.info("Syncd ${count}/${dates.size} exchange rate days in ${executionTime}ms")
                }
    }
}