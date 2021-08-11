package com.github.cetonek.bigbiznis.unemploymentrate.domain

import com.github.cetonek.bigbiznis.unemploymentrate.data.database.UnemploymentRateRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class FetchUnemploymentRateUseCase(private val repository: UnemploymentRateRepository) {

    @Cacheable("FetchUnemploymentRateUseCase::fetchAllUnempRatesAveragedByYear")
    fun fetchAllUnempRatesAveragedByYear() = repository.getAllYearlyAveragedUnemploymentRates()

    @Cacheable("FetchUnemploymentRateUseCase::fetchAllMonthlyUnempRates")
    fun fetchAllMonthlyUnempRates() = repository.findAll()

}