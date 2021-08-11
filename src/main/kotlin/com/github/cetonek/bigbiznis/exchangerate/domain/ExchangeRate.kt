package com.github.cetonek.bigbiznis.exchangerate.domain

import java.time.LocalDate

data class ExchangeRate(
        var date: LocalDate,
        var currencyCode: String,
        var currencyName: String,
        var amount: Int,
        var exchangeRate: Double,
        var country: String
)