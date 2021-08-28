package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.core.db.BigBiznisRepository
import com.github.cetonek.bigbiznis.domain.entity.persisted.BudgetBalance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BudgetBalanceRepository : BigBiznisRepository<BudgetBalance, Long> {

    fun findFirstByOrderByYearDesc(): BudgetBalance

}