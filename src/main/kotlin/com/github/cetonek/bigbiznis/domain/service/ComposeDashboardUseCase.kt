package com.github.cetonek.bigbiznis.domain.service

import com.github.cetonek.bigbiznis.application.utility.utility.mapToPairs
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProductType.*
import org.springframework.stereotype.Service

@Service
class ComposeDashboardUseCase(private val fetchGdp: FetchGrossDomesticProductUseCase,
                              private val fetchUnemploymentRate: FetchUnemploymentRateUseCase,
                              private val fetchInflation: FetchInflationRateUseCase,
                              private val fetchNationalBudget: FetchNationalBudgetUseCase,
                              private val composeOverview: ComposeEconomyOverviewUseCase
) {


    fun execute(): EconomyDashboard {

        val unemployment = fetchUnemploymentRate.fetchAllUnempRatesAveragedByYear()
        val inflation = fetchInflation.fetchAllYearlyInflationRates()
        val publicDebt = fetchNationalBudget.fetchPublicDebt()
        val overview = composeOverview.execute()
        val gdp = fetchGdp.fetchPercentChangesPerYear(REAL_2010_PRICES)

        return EconomyDashboard(
                overview = overview,
                yearlyUnempRates = unemployment.mapToPairs(),
                yearlyInflationRates = inflation.mapToPairs(),
                realGdp2010PricesPercentChange = gdp.mapToPairs(),
                publicDebt = publicDebt.mapToPairs()
        )

    }

    data class EconomyDashboard(val overview: EconomyOverview,
                                val realGdp2010PricesPercentChange: List<Pair<Any, Any>>,
                                val yearlyUnempRates: List<Pair<Any, Any>>,
                                val yearlyInflationRates: List<Pair<Any, Any>>,
                                val publicDebt: List<Pair<Any, Any>>)

}