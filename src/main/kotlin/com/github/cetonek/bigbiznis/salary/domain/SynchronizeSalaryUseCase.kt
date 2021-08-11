package com.github.cetonek.bigbiznis.salary.domain

import com.github.cetonek.bigbiznis.core.data.api.CNBClient
import com.github.cetonek.bigbiznis.salary.data.api.toEntity
import com.github.cetonek.bigbiznis.salary.data.database.SalaryRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SynchronizeSalaryUseCase(private val api: CNBClient,
                               private val repository: SalaryRepository) {

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