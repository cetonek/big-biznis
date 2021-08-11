package com.github.cetonek.bigbiznis.exchangerate.presentation

import com.github.cetonek.bigbiznis.core.presentation.model.Routing
import com.github.cetonek.bigbiznis.exchangerate.domain.ExchangeRate
import com.github.cetonek.bigbiznis.exchangerate.domain.FetchExchangeRateUseCase
import com.github.cetonek.bigbiznis.utility.exampleRate
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher.matchAll
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate

@WebMvcTest(controllers = [ExchangeRateController::class])
class ExchangeRateControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var useCaseFetch: FetchExchangeRateUseCase

    @Test
    fun `GET kurzy USD returns model with usd currencies only`() {
        // given
        val currencyCode = "USD"
        val expectedRates = listOf(ExchangeRate(LocalDate.now(), currencyCode, "dolar", 1, 22.5, "USA"))

        given(useCaseFetch.fetchByCurrencyOrderByDate(currencyCode)).willReturn(expectedRates)
        // when
        // then
        mvc.perform(get("${Routing.EXCHANGE_RATE}/${currencyCode}"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.parseMediaType("text/html;charset=UTF8")))
                .andExpect(model().attribute("rates", expectedRates))
    }

    @Test
    fun `GET kurzy UNKNOWN currency returns 404 model`() {
        // given
        val currencyCode = "UNKNOWN"

        given(useCaseFetch.fetchByCurrencyOrderByDate(currencyCode)).willReturn(listOf())
        // when
        // then
        mvc.perform(get("${Routing.EXCHANGE_RATE}/${currencyCode}"))
                .andExpect(matchAll(
                        status().isNotFound,
                        model().attributeDoesNotExist("rates"),
                        model().attribute("status", 404),
                        view().name("error/4xx")
                ))
    }

    @Test
    fun `GET kurzy return correct template`() {
        // given
        given(useCaseFetch.fetchLatestRates())
                .willReturn(listOf(exampleRate))
        // when
        // then
        mvc.perform(get(Routing.EXCHANGE_RATE))
                .andExpect(matchAll(
                        status().isOk,
                        view().name("pages/exchangerate")
                ))
    }

    @Test
    fun `GET kurzy USD return correct template`() {
        // given
        given(useCaseFetch.fetchByCurrencyOrderByDate("USD"))
                .willReturn(listOf(exampleRate))
        // when
        // then
        mvc.perform(get("${Routing.EXCHANGE_RATE}/USD"))
                .andExpect(matchAll(
                        status().isOk,
                        view().name("pages/exchangerate_detail")
                ))
    }


}