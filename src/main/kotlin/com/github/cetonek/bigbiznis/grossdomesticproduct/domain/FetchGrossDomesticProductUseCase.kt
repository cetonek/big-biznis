package com.github.cetonek.bigbiznis.grossdomesticproduct.domain

import com.github.cetonek.bigbiznis.core.domain.CalculateIncrementalValueChangesUseCase
import com.github.cetonek.bigbiznis.core.domain.InputData
import com.github.cetonek.bigbiznis.core.domain.OutputPercentageData
import com.github.cetonek.bigbiznis.grossdomesticproduct.data.database.GrossDomesticProductEntity
import com.github.cetonek.bigbiznis.grossdomesticproduct.data.database.GrossDomesticProductRepository
import com.github.cetonek.bigbiznis.grossdomesticproduct.data.database.GrossDomesticProductType
import com.github.cetonek.bigbiznis.grossdomesticproduct.domain.model.GrossDomesticProductByYear
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class FetchGrossDomesticProductUseCase(private val repository: GrossDomesticProductRepository,
                                       private val calculateChanges: CalculateIncrementalValueChangesUseCase) {

    @Cacheable("FetchGrossDomesticProductUseCase::fetchGdp")
    fun fetchGdp(type: GrossDomesticProductType = GrossDomesticProductType.REAL_2010_PRICES) = repository
            .getAllByTypeEquals(type)

    @Cacheable("FetchGrossDomesticProductUseCase::fetchPercentChangesPerYear")
    fun fetchPercentChangesPerYear(type: GrossDomesticProductType = GrossDomesticProductType.REAL_2010_PRICES)
            : List<OutputPercentageData<GrossDomesticProductByYear>> {
        return repository.getAllSummedByYearHavingAllFourQuarters(type)
                .map { InputData(it.year.toLong(), it.gdpMillions, it) }
                .let { calculateChanges.calculatePercentageChanges(it) }
    }

    @Cacheable("FetchGrossDomesticProductUseCase::fetchPercentChangesPerQuarter")
    fun fetchPercentChangesPerQuarter(type: GrossDomesticProductType = GrossDomesticProductType.REAL_2010_PRICES)
            : List<OutputPercentageData<GrossDomesticProductEntity>> {
        return repository.getAllByTypeEquals(type)
                .mapIndexed { index, gdp -> Pair(index, gdp) }
                .map { InputData(it.first.toLong(), it.second.gdpMillionsCrowns, it.second) }
                .let { calculateChanges.calculatePercentageChanges(it) }
    }


}