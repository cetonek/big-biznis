package com.github.cetonek.bigbiznis.unemploymentrate.presentation

import com.github.cetonek.bigbiznis.application.utility.model.Home
import com.github.cetonek.bigbiznis.application.utility.model.Routing
import com.github.cetonek.bigbiznis.application.utility.model.Unemployment
import com.github.cetonek.bigbiznis.domain.entity.persisted.UnemploymentRate
import com.github.cetonek.bigbiznis.domain.service.FetchUnemploymentRateUseCase
import com.github.cetonek.bigbiznis.utility.breadcrumbs
import com.github.cetonek.bigbiznis.application.web.mvc.UnemploymentController
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(controllers = [UnemploymentController::class])
class UnemploymentControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var fetchUnemp: FetchUnemploymentRateUseCase

    val quarterlyUnemployment = listOf(UnemploymentRate(2015, 1, 5.3f))

    @BeforeEach
    fun setUp() {
        given(fetchUnemp.fetchAllMonthlyUnempRates())
                .willReturn(quarterlyUnemployment)
    }

    @Test
    fun `GET hdp returns correct page`() {
        // given
        // when
        // then
        mvc.perform(get(Routing.UNEMPLOYMENT))
                .andExpect(
                        matchAll(
                                status().isOk,
                                view().name("pages/unemployment")
                        ))

    }

    @Test
    fun `GET hdp returns correct breadcrumbs`() {
        // given
        // when
        // then
        mvc.perform(get(Routing.UNEMPLOYMENT))
                .andExpect(
                        matchAll(
                                status().isOk,
                                breadcrumbs(Home, Unemployment)
                        ))

    }


}