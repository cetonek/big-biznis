package com.github.cetonek.bigbiznis.domain.service

import com.github.cetonek.bigbiznis.application.configuration.ExchangeRateConfiguration
import com.github.cetonek.bigbiznis.application.utility.getLogger
import com.github.cetonek.bigbiznis.domain.repository.ExchangeRateRepository
import com.github.cetonek.bigbiznis.integration.CNBClient
import com.github.cetonek.bigbiznis.integration.ExchangeRateRootDto
import com.github.cetonek.bigbiznis.integration.toDomain
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

@Service
class ExchangeRateService(
        private val cnbClient: CNBClient,
        private val exchangeRepository: ExchangeRateRepository,
        private val configuration: ExchangeRateConfiguration) {

    private val LOGGER = getLogger(this::class.java)

    fun synchronizeTodaysExchangeRates() = executeSynchForDates(listOf(LocalDate.now()))

    fun synchronizeAllExchangeRates() {
        executeSynchForDates(exchangeRepository.findAllWeekDaysThatAreMissing(configuration.largeSyncStartingDate)
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
                    LOGGER.info("Syncd ${count}/${dates.size} exchange rate days in ${executionTime}ms")
                }
    }
}