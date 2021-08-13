package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.domain.entity.persisted.refactored.BudgetBalance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BudgetBalanceRepository : JpaRepository<BudgetBalance, Long> {

    fun findFirstByOrderByYearDesc(): BudgetBalance

}