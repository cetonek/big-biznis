package com.github.cetonek.bigbiznis.domain.service

import com.github.cetonek.bigbiznis.integration.cnb.CNBClient
import com.github.cetonek.bigbiznis.integration.cnb.toEntity
import com.github.cetonek.bigbiznis.domain.repository.UnemploymentRateRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SynchronizeUnemploymentRateUseCase(private val api: CNBClient,
                                         private val repository: UnemploymentRateRepository
) {


    private val startingDate = LocalDate.of(1998, 1, 1)

    fun execute() {
        api.fetchMonthlyUnemploymentRates(from = startingDate, to = LocalDate.now())
                .map { it.toEntity() }
                .toList()
                .also { repository.saveAll(it) }
    }

}