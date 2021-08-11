package com.github.cetonek.bigbiznis.inflation.domain

import com.github.cetonek.bigbiznis.inflation.data.InflationRateRepository
import com.github.cetonek.bigbiznis.inflation.data.InflationType
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class FetchInflationRateUseCase (private val repository: InflationRateRepository) {

    @Cacheable("FetchInflationRateUseCase::fetchAllYearlyInflationRates")
    fun fetchAllYearlyInflationRates() =
            repository.findAllByTypeAndMonthEquals(InflationType.THIS_YEAR_VS_LAST_YEAR, 12)

    @Cacheable("FetchInflationRateUseCase::fetchAllInflationRatesByType")
    fun fetchAllInflationRatesByType(type: InflationType) = repository.findAllByTypeEquals(type)

}