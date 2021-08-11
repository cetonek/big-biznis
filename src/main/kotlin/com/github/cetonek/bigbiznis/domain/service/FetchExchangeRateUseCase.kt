package com.github.cetonek.bigbiznis.domain.service

import com.github.cetonek.bigbiznis.domain.entity.ExchangeRate
import com.github.cetonek.bigbiznis.domain.repository.ExchangeRateRepository
import com.github.cetonek.bigbiznis.domain.entity.persisted.toDomain
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class FetchExchangeRateUseCase(private val repository: ExchangeRateRepository,
                               private val synchronizeExchangeRate: SynchronizeExchangeRateUseCase
) {

    @Cacheable("FetchExchangeRateUseCase::fetchLatestRates")
    fun fetchLatestRates(): Collection<ExchangeRate> {
        val latestRates = repository.findAllRatesFromLastDay()
        latestRates.ifEmpty {
            synchronizeExchangeRate.executeForToday()
            repository.findAllRatesFromLastDay()
        }
        return latestRates.map {
            it.toDomain()
        }
    }

    @Cacheable("FetchExchangeRateUseCase::fetchByCurrencyOrderByDate")
    fun fetchByCurrencyOrderByDate(currencyCode: String): List<ExchangeRate> {
        return repository.findExchangeRateEntityByCurrencyCodeOrderByDate(currencyCode)
                .map { it.toDomain() }
    }

}