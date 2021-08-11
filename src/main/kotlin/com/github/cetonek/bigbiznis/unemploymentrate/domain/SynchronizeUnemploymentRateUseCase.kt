package com.github.cetonek.bigbiznis.unemploymentrate.domain

import com.github.cetonek.bigbiznis.core.data.api.CNBClient
import com.github.cetonek.bigbiznis.unemploymentrate.data.api.toEntity
import com.github.cetonek.bigbiznis.unemploymentrate.data.database.UnemploymentRateRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SynchronizeUnemploymentRateUseCase(private val api: CNBClient,
                                         private val repository: UnemploymentRateRepository) {


    private val startingDate = LocalDate.of(1998, 1, 1)

    fun execute() {
        api.fetchMonthlyUnemploymentRates(from = startingDate, to = LocalDate.now())
                .map { it.toEntity() }
                .toList()
                .also { repository.saveAll(it) }
    }

}