package com.github.cetonek.bigbiznis.domain.service

import com.github.cetonek.bigbiznis.domain.entity.persisted.ExchangeRate
import com.github.cetonek.bigbiznis.domain.repository.ExchangeRateRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class FetchExchangeRateUseCase(private val repository: ExchangeRateRepository,
                               private val synchronizeExchangeRate: ExchangeRateService
) {

    @Cacheable("FetchExchangeRateUseCase::fetchLatestRates")
    fun fetchLatestRates(): Collection<ExchangeRate> {
        val latestRates = repository.findAllRatesFromLastDay()
        latestRates.ifEmpty {
            synchronizeExchangeRate.synchronizeTodaysExchangeRates()
            repository.findAllRatesFromLastDay()
        }
        return latestRates
    }

    @Cacheable("FetchExchangeRateUseCase::fetchByCurrencyOrderByDate")
    fun fetchByCurrencyOrderByDate(currencyCode: String): List<ExchangeRate> {
        return repository.findExchangeRateEntityByCurrencyCodeOrderByDate(currencyCode)
    }

}