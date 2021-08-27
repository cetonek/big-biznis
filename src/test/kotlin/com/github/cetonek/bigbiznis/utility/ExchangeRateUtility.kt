package com.github.cetonek.bigbiznis.utility

import com.github.cetonek.bigbiznis.domain.entity.ExchangeRate
import com.github.cetonek.bigbiznis.domain.service.ComposeDashboardUseCase
import com.github.cetonek.bigbiznis.domain.service.FetchExchangeRateUseCase
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProductEntity
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProductType
import com.github.cetonek.bigbiznis.domain.service.FetchGrossDomesticProductUseCase
import com.github.cetonek.bigbiznis.domain.service.FetchUnemploymentRateUseCase
import com.github.cetonek.bigbiznis.domain.entity.UnemploymentRatePerYearAvg
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

fun FetchGrossDomesticProductUseCase.mockGDP(type: GrossDomesticProductType, gdps: List<GrossDomesticProductEntity>) {
    given(this.fetchGdp(type)).willReturn(gdps)
}

fun FetchUnemploymentRateUseCase.mockUnemployment(unemployment: List<UnemploymentRatePerYearAvg>) {
    given(this.fetchAllUnempRatesAveragedByYear()).willReturn(unemployment)
}

fun ComposeDashboardUseCase.mockDashboard(dashboard: ComposeDashboardUseCase.EconomyDashboard) {
    given(this.execute()).willReturn(dashboard)
}
