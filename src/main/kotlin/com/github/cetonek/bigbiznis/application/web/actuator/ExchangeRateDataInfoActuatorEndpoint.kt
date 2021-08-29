package com.github.cetonek.bigbiznis.application.web.actuator

import com.github.cetonek.bigbiznis.application.configuration.BigBiznisConfiguration
import com.github.cetonek.bigbiznis.domain.repository.ExchangeRateRepository
import com.github.cetonek.bigbiznis.application.configuration.ExchangeRateConfiguration
import org.springframework.boot.actuate.endpoint.annotation.Endpoint
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation
import org.springframework.stereotype.Component
import java.time.LocalDate


@Component
@Endpoint(id = "exchangeRateData")
class ExchangeRateDataInfoActuatorEndpoint(private val repository: ExchangeRateRepository,
                                           private val secondConfig: ExchangeRateConfiguration
) {


    @ReadOperation
    fun getInfo(): ExchangeRateDataInfo {
        val totalRows = repository.count()
        val daysWithMissingRates = repository.findAllWeekDaysThatAreMissing(secondConfig.largeSyncStartingDate)
                .size

        val oldestRecordDate = repository.findFirstByOrderByDate().date

        return ExchangeRateDataInfo(totalRecords = totalRows,
                daysWithMissingRates = daysWithMissingRates,
                oldestRecordDate = oldestRecordDate)
    }


}

data class ExchangeRateDataInfo(val totalRecords: Long,
                                val daysWithMissingRates: Int,
                                val oldestRecordDate: LocalDate)