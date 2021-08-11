package com.github.cetonek.bigbiznis.nationalbudget.domain

import com.github.cetonek.bigbiznis.nationalbudget.data.BudgetBalanceEntity
import com.github.cetonek.bigbiznis.nationalbudget.data.BudgetBalanceRepository
import com.github.cetonek.bigbiznis.nationalbudget.data.PublicDebtEntity
import com.github.cetonek.bigbiznis.nationalbudget.data.PublicDebtRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class FetchNationalBudgetUseCase(private val budgetBalanceRepository: BudgetBalanceRepository,
                                 private val publicDebtRepository: PublicDebtRepository) {

    @Cacheable("FetchNationalBudgetUseCase::fetchPublicDebt")
    fun fetchPublicDebt(): List<PublicDebtEntity> {
        return publicDebtRepository.findAll()
    }

    @Cacheable("FetchNationalBudgetUseCase::findCurrentPublicDebt")
    fun findCurrentPublicDebt() : PublicDebtEntity = publicDebtRepository.findFirstByOrderByYearDesc()

    @Cacheable("FetchNationalBudgetUseCase::fetchBudgetBalance")
    fun fetchBudgetBalance(): List<BudgetBalanceEntity> {
        return budgetBalanceRepository.findAll()
    }

    @Cacheable("FetchNationalBudgetUseCase::fetchCurrentBudgetBalance")
    fun fetchCurrentBudgetBalance() = budgetBalanceRepository.findFirstByOrderByYearDesc()

}