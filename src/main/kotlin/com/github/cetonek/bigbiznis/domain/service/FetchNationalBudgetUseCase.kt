package com.github.cetonek.bigbiznis.domain.service

import com.github.cetonek.bigbiznis.domain.entity.persisted.BudgetBalance
import com.github.cetonek.bigbiznis.domain.repository.BudgetBalanceRepository
import com.github.cetonek.bigbiznis.domain.entity.persisted.PublicDebt
import com.github.cetonek.bigbiznis.domain.repository.PublicDebtRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class FetchNationalBudgetUseCase(private val budgetBalanceRepository: BudgetBalanceRepository,
                                 private val publicDebtRepository: PublicDebtRepository
) {

    @Cacheable("FetchNationalBudgetUseCase::fetchPublicDebt")
    fun fetchPublicDebt(): List<PublicDebt> {
        return publicDebtRepository.findAll()
    }

    @Cacheable("FetchNationalBudgetUseCase::findCurrentPublicDebt")
    fun findCurrentPublicDebt(): PublicDebt = publicDebtRepository.findFirstByOrderByYearDesc()

    @Cacheable("FetchNationalBudgetUseCase::fetchBudgetBalance")
    fun fetchBudgetBalance(): List<BudgetBalance> {
        return budgetBalanceRepository.findAll()
    }

    @Cacheable("FetchNationalBudgetUseCase::fetchCurrentBudgetBalance")
    fun fetchCurrentBudgetBalance() = budgetBalanceRepository.findFirstByOrderByYearDesc()

}