package com.github.cetonek.bigbiznis.domain.service

import com.github.cetonek.bigbiznis.domain.repository.AverageSalaryRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class FetchSalaryUseCase(private val repository: AverageSalaryRepository) {

    @Cacheable("FetchSalaryUseCase::fetchAll", unless = "#result.isEmpty()")
    fun fetchAll() = repository.findAll().toList()

    @Cacheable("FetchSalaryUseCase::fetchLatest", unless = "#result == null")
    fun fetchLatest() = repository.findFirstByOrderByYearDescQuarterDesc()

}