package com.github.cetonek.bigbiznis.application.web.rest

import com.github.cetonek.bigbiznis.application.web.rest.EconomyRestController
import com.github.cetonek.bigbiznis.domain.service.FetchExchangeRateUseCase
import com.github.cetonek.bigbiznis.application.utility.exampleRate
import com.github.cetonek.bigbiznis.application.utility.mockLatestRates
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@Suppress("MemberVisibilityCanBePrivate")
@WebMvcTest(controllers = [EconomyRestController::class])
class EconomyRestControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @MockBean
    lateinit var useCaseFetch: FetchExchangeRateUseCase

    @Test
    fun `GET exchangerate returns rates from service`() {
        // given
        val mockRates = listOf(exampleRate)
        useCaseFetch.mockLatestRates(mockRates)

        val expectedEntity = EconomyRestController.ExchangeRatesResponse(mockRates)
        val expectedJsonResponse = mapper.writeValueAsString(expectedEntity)
        // when
        // then
        mvc.perform(get("/api/v1/exchangerate")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJsonResponse))

    }

}