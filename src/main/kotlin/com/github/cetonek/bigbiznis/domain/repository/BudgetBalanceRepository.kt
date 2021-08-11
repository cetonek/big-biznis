package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.domain.entity.persisted.BudgetBalanceEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BudgetBalanceRepository : JpaRepository<BudgetBalanceEntity, Int> {

    fun findFirstByOrderByYearDesc(): BudgetBalanceEntity

}