package com.github.cetonek.bigbiznis.application.web.rest

import com.github.cetonek.bigbiznis.domain.entity.persisted.ExchangeRate
import com.github.cetonek.bigbiznis.domain.service.FetchExchangeRateUseCase
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

    data class ExchangeRatesResponse(val rates: Collection<ExchangeRate>)

}
