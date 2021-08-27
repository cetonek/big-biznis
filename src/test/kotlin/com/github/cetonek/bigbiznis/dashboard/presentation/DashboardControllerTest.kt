package com.github.cetonek.bigbiznis.dashboard.presentation

import com.github.cetonek.bigbiznis.domain.service.OutputPercentageData
import com.github.cetonek.bigbiznis.application.utility.utility.mapToPairs
import com.github.cetonek.bigbiznis.domain.service.ComposeDashboardUseCase
import com.github.cetonek.bigbiznis.domain.service.EconomyOverview
import com.github.cetonek.bigbiznis.domain.service.ExchangeRatesOverview
import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationRate
import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationType
import com.github.cetonek.bigbiznis.domain.entity.persisted.PublicDebt
import com.github.cetonek.bigbiznis.domain.entity.UnemploymentRatePerYearAvg
import com.github.cetonek.bigbiznis.utility.exampleRate
import com.github.cetonek.bigbiznis.utility.mockDashboard
import com.github.cetonek.bigbiznis.application.web.mvc.DashboardController
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate

@Suppress("MemberVisibilityCanBePrivate")
@WebMvcTest(controllers = [DashboardController::class])
class DashboardControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var composeDashboard: ComposeDashboardUseCase

    val gdp = listOf(OutputPercentageData(order = 2015, value = 5.5, dataPoint = 0))
    val unemp = listOf(UnemploymentRatePerYearAvg(2015, unemploymentRatePercent = 5.7))

    val inflation = listOf(
            InflationRate(month = 12, year = 2015, type = InflationType.THIS_YEAR_VS_LAST_YEAR,
                    inflationPercent = 5f)
    )

    val publicDebt = listOf(PublicDebt(year = 2015, millionsCrowns = 1564654))

    val overview = EconomyOverview(
            exchangeRate = ExchangeRatesOverview(LocalDate.now(), listOf(exampleRate)),
            firstOverviewItems = listOf(),
            secondOverviewItems = listOf())

    val expectedDashboard = ComposeDashboardUseCase.EconomyDashboard(
            overview = overview,
            realGdp2010PricesPercentChange = gdp.mapToPairs(),
            publicDebt = publicDebt.mapToPairs(),
            yearlyInflationRates = inflation.mapToPairs(),
            yearlyUnempRates = unemp.mapToPairs()
    )


    @Test
    fun `GET root route returns dashboard`() {
        // given
        composeDashboard.mockDashboard(expectedDashboard)

        // when
        // then
        mvc.perform(get("/"))
                .andExpect(ResultMatcher.matchAll(
                        status().isOk,
                        model().attribute("dashboard", expectedDashboard),
                        view().name("pages/dashboard")
                ))
    }

    @Test
    fun `GET that results in internal server error is handled`() {
        // given
        given(composeDashboard.execute()).willThrow(RuntimeException("whoops"))
        // when
        // then
        mvc.perform(get("/"))
                .andExpect(ResultMatcher.matchAll(
                        status().isInternalServerError,
                        model().attributeDoesNotExist("dashboard"),
                        model().attribute("status", 500),
                        view().name("error/5xx")
                ))
    }


}