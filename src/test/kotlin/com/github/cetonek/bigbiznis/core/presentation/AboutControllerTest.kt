package com.github.cetonek.bigbiznis.core.presentation

import com.github.cetonek.bigbiznis.domain.service.FetchDataSourcesUseCase
import com.github.cetonek.bigbiznis.domain.service.Inflation
import com.github.cetonek.bigbiznis.domain.service.RealGdp
import com.github.cetonek.bigbiznis.application.web.mvc.AboutController
import com.github.cetonek.bigbiznis.application.utility.model.About
import com.github.cetonek.bigbiznis.application.utility.model.Home
import com.github.cetonek.bigbiznis.application.utility.model.Routing
import com.github.cetonek.bigbiznis.utility.breadcrumbs
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher.matchAll
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(controllers = [AboutController::class])
class AboutControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var fetchDataSources: FetchDataSourcesUseCase

    val dataSources = listOf(RealGdp, Inflation)

    @BeforeEach
    fun setUp() {
        given(fetchDataSources.execute()).willReturn(dataSources)
    }

    @Test
    fun `GET oaplikaci returns correct page and breadcrumbs`() {
        // given
        // when
        // then
        mvc.perform(get(Routing.ABOUT))
                .andExpect(
                        matchAll(
                                status().isOk,
                                breadcrumbs(Home, About),
                                view().name("pages/about")
                        )
                )
    }

    @Test
    fun `GET oaplikaci zdrojedat add correct data sources to model`() {
        // given
        // when
        // then
        mvc.perform(get(Routing.ABOUT))
                .andExpect(
                        matchAll(
                                status().isOk,
                                model().attribute("dataSources", dataSources)
                        )
                )
    }


}