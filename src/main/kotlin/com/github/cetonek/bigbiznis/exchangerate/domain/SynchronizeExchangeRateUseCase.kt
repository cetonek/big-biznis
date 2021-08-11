package com.github.cetonek.bigbiznis.exchangerate.domain

import com.github.cetonek.bigbiznis.core.data.api.CNBClient
import com.github.cetonek.bigbiznis.core.domain.getLogger
import com.github.cetonek.bigbiznis.exchangerate.data.api.ExchangeRateRootDto
import com.github.cetonek.bigbiznis.exchangerate.data.api.toDomain
import com.github.cetonek.bigbiznis.exchangerate.data.database.ExchangeRateRepository
import com.github.cetonek.bigbiznis.exchangerate.data.database.toEntity
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.streams.toList

@Service
class SynchronizeExchangeRateUseCase(private val cnbClient: CNBClient,
                                     private val exchangeRepository: ExchangeRateRepository,
                                     private val configuration: ExchangeRateConfiguration) {

    private val LOGGER = getLogger(this::class.java)

    fun executeForToday() = executeSynchForDates(listOf(LocalDate.now()))

    fun executeForAllMissingDays() {
        val dates = exchangeRepository.findAllWeekdaysThatAreMissing(configuration.largeSyncStartingDate)
                // todo convert date in jpa
                .map {
                    LocalDate.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                }
        executeSynchForDates(dates)
    }

    private fun executeSynchForDates(dates: Collection<LocalDate>) {
        val syncStart = System.currentTimeMillis()
        val count = AtomicInteger(0)
        dates
                .parallelStream()
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
                .map { it.toEntity() }
                .toList()
                .also { exchangeRepository.saveAll(it) }
                .also {
                    val executionTime = System.currentTimeMillis() - syncStart
                    LOGGER.info("Syncd ${count}/${dates.size} exchange rate days in ${executionTime}ms")
                }
    }
}