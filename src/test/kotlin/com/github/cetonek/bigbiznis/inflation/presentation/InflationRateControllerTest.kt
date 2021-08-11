package com.github.cetonek.bigbiznis.inflation.presentation

import com.github.cetonek.bigbiznis.core.presentation.model.Home
import com.github.cetonek.bigbiznis.core.presentation.model.Inflation
import com.github.cetonek.bigbiznis.core.presentation.model.Routing
import com.github.cetonek.bigbiznis.inflation.data.InflationRateEntity
import com.github.cetonek.bigbiznis.inflation.data.InflationType.*
import com.github.cetonek.bigbiznis.inflation.domain.FetchInflationRateUseCase
import com.github.cetonek.bigbiznis.utility.breadcrumbs
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(controllers = [InflationRateController::class])
class InflationRateControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var fetchInflation: FetchInflationRateUseCase

    val inflation = InflationRateEntity(
            month = 12,
            year = 2015,
            type = THIS_YEAR_VS_LAST_YEAR,
            valuePercent = 5.5f)

    @BeforeEach
    fun setUp() {

        given(fetchInflation.fetchAllInflationRatesByType(THIS_MONTH_VS_PREVIOUS_MONTH))
                .willReturn(listOf(inflation.copy(type = THIS_MONTH_VS_PREVIOUS_MONTH)))

        given(fetchInflation.fetchAllInflationRatesByType(THIS_YEAR_VS_LAST_YEAR))
                .willReturn(listOf(inflation.copy(type = THIS_YEAR_VS_LAST_YEAR)))

        given(fetchInflation.fetchAllInflationRatesByType(THIS_MONTH_VS_PREVIOUS_YEARS_MONTH))
                .willReturn(listOf(inflation.copy(type = THIS_MONTH_VS_PREVIOUS_YEARS_MONTH)))

    }

    @Test
    fun `GET inflace returns correct page`() {
        // given
        // when
        // then
        mvc.perform(get(Routing.INFLATION))
                .andExpect(
                        matchAll(
                                status().isOk,
                                view().name("pages/inflation")
                        ))

    }

    @Test
    fun `GET inflace returns correct breadcrumbs`() {
        // given
        // when
        // then
        mvc.perform(get(Routing.INFLATION))
                .andExpect(matchAll(
                        breadcrumbs(Home, Inflation)
                ))

    }


}