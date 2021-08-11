package com.github.cetonek.bigbiznis.dashboard.domain

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "dashboard.overview")
class EconomyOverviewConfiguration {

    var exchangeRates: List<String> = listOf("USD", "GBP", "EUR")

}