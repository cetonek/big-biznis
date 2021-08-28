package com.github.cetonek.bigbiznis.application.web.mvc

import com.github.cetonek.bigbiznis.domain.service.OutputPercentageData
import com.github.cetonek.bigbiznis.application.utility.model.Gdp
import com.github.cetonek.bigbiznis.application.utility.model.Home
import com.github.cetonek.bigbiznis.application.utility.model.Routing
import com.github.cetonek.bigbiznis.application.utility.utility.mapToPairs
import com.github.cetonek.bigbiznis.application.utility.utility.quarterToRoman
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProduct
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProductType.*
import com.github.cetonek.bigbiznis.domain.service.FetchGrossDomesticProductUseCase
import com.github.cetonek.bigbiznis.application.utility.breadcrumbs
import com.github.cetonek.bigbiznis.application.web.mvc.GrossDomesticProductController
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher.matchAll
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(controllers = [GrossDomesticProductController::class])
class GrossDomesticProductControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var fetchGdp: FetchGrossDomesticProductUseCase

    val realGdp = listOf(
            GrossDomesticProduct(
                    year = 2020,
                    type = REAL_2010_PRICES,
                    gdpMillionsCrowns = 464654,
                    quarter = 3
            )
    )

    val realChanges = listOf(
            OutputPercentageData(2015, 4.0, GrossDomesticProduct(
                    year = 2015, quarter = 4, gdpMillionsCrowns = 54564, type = REAL_2010_PRICES)
            )
    )

    @BeforeEach
    fun setUp() {

        given(fetchGdp.fetchGdp(REAL_2010_PRICES))
                .willReturn(realGdp)


        given(fetchGdp.fetchPercentChangesPerQuarter(REAL_2010_PRICES))
                .willReturn(realChanges)

    }

    @Test
    fun `GET hdp returns correct page`() {
        // given
        // when
        // then
        mvc.perform(get(Routing.GDP))
                .andExpect(
                        matchAll(
                                status().isOk,
                                view().name("pages/gdp")
                        ))

    }

    @Test
    fun `GET hdp returns correct model`() {
        // given
        // when
        // then
        mvc.perform(get(Routing.GDP))
                .andExpect(
                        matchAll(
                                model().attribute("realGdp2010Prices", realGdp
                                        .mapToPairs { Pair("${it.quarter.quarterToRoman()} ${it.year}", it.gdpMillionsCrowns) }),
                                model().attribute("realGdpChanges", realChanges.mapToPairs {
                                    Pair("${it.dataPoint.quarter.quarterToRoman()} ${it.dataPoint.year}", it.value)
                                })
                        ))

    }

    @Test
    fun `GET hdp returns correct breadcrumbs`() {
        // given
        // when
        // then
        mvc.perform(get(Routing.GDP))
                .andExpect(
                        matchAll(
                                breadcrumbs(Home, Gdp)
                        ))

    }


}