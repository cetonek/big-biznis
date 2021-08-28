package com.github.cetonek.bigbiznis.domain.service

import com.github.cetonek.bigbiznis.integration.cnb.CNBClient
import com.github.cetonek.bigbiznis.integration.cnb.toEntity
import com.github.cetonek.bigbiznis.domain.repository.AverageSalaryRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SynchronizeSalaryUseCase(private val api: CNBClient,
                               private val repository: AverageSalaryRepository
) {

    private val startingDate = LocalDate.of(2000, 1, 1)

    fun execute() {
        api.fetchNominalAverageSalaries(from = startingDate, to = LocalDate.now())
                .map { it.toEntity() }
                .toList()
                .also {
                    repository.saveAll(it)
                }
    }

}