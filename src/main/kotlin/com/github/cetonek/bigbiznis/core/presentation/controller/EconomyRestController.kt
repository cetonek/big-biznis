package com.github.cetonek.bigbiznis.core.presentation.controller

import com.github.cetonek.bigbiznis.exchangerate.domain.ExchangeRate
import com.github.cetonek.bigbiznis.exchangerate.domain.FetchExchangeRateUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class EconomyRestController(private val fetchExchangeUseCase: FetchExchangeRateUseCase) {

    @GetMapping("/exchangerate")
    fun getExchangeRatesApi(): ExchangeRatesResponse {
        return ExchangeRatesResponse(fetchExchangeUseCase.fetchLatestRates())
    }

    data class ExchangeRatesResponse (val rates: Collection<ExchangeRate>)

}
