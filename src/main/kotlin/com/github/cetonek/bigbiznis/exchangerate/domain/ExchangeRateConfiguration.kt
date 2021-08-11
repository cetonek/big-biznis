package com.github.cetonek.bigbiznis.exchangerate.domain

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.validation.annotation.Validated
import java.time.LocalDate

@Validated
@ConfigurationProperties(prefix = "exchangerate")
class ExchangeRateConfiguration {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var largeSyncStartingDate: LocalDate = LocalDate.of(1991, 1,1)

}