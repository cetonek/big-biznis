package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.domain.entity.persisted.AverageSalary
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface AverageSalaryRepository : JpaRepository<AverageSalary, Long> {

    @Transactional(readOnly = true)
    fun findFirstByOrderByYearDescQuarterDesc(): AverageSalary

}