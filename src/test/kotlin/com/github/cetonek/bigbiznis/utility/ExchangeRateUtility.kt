package com.github.cetonek.bigbiznis.utility

import com.github.cetonek.bigbiznis.dashboard.domain.ComposeDashboardUseCase
import com.github.cetonek.bigbiznis.exchangerate.domain.ExchangeRate
import com.github.cetonek.bigbiznis.exchangerate.domain.FetchExchangeRateUseCase
import com.github.cetonek.bigbiznis.grossdomesticproduct.data.database.GrossDomesticProductEntity
import com.github.cetonek.bigbiznis.grossdomesticproduct.data.database.GrossDomesticProductType
import com.github.cetonek.bigbiznis.grossdomesticproduct.domain.FetchGrossDomesticProductUseCase
import com.github.cetonek.bigbiznis.unemploymentrate.domain.FetchUnemploymentRateUseCase
import com.github.cetonek.bigbiznis.unemploymentrate.domain.model.UnemploymentRatePerYearAvg
import org.mockito.BDDMockito.given
import java.time.LocalDate

fun FetchExchangeRateUseCase.mockLatestRates(rates: Collection<ExchangeRate>) {
    given(this.fetchLatestRates()).willReturn(rates)
}

val exampleRate = ExchangeRate(date = LocalDate.now(),
        currencyCode = "USD",
        currencyName = "dolar",
        amount = 1,
        exchangeRate = 25.5,
        country = "USA")

fun FetchGrossDomesticProductUseCase.mockGDP(type: GrossDomesticProductType ,gdps: List<GrossDomesticProductEntity>) {
    given(this.fetchGdp(type)).willReturn(gdps)
}

fun FetchUnemploymentRateUseCase.mockUnemployment(unemployment: List<UnemploymentRatePerYearAvg>) {
    given(this.fetchAllUnempRatesAveragedByYear()).willReturn(unemployment)
}

fun ComposeDashboardUseCase.mockDashboard(dashboard: ComposeDashboardUseCase.EconomyDashboard) {
    given(this.execute()).willReturn(dashboard)
}
