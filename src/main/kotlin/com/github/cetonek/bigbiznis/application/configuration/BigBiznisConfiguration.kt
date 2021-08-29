package com.github.cetonek.bigbiznis.application.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.validation.annotation.Validated
import java.time.LocalDate
import javax.validation.constraints.NotEmpty

@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "bb")
class BigBiznisConfiguration (
        val exchangeRate: ExchangeRateConfiguration,
        val dashboard: DashboardConfiguration
)

@ConfigurationProperties
class ExchangeRateConfiguration {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    lateinit var largeSyncStartingDate: LocalDate

}

@ConfigurationProperties(prefix = "bb.dashboard")
class DashboardConfiguration {

    @NotEmpty
    lateinit var exchangeRates: List<String>


}