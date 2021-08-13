package com.github.cetonek.bigbiznis.nationalbudget.data

import com.github.cetonek.bigbiznis.DatabaseTest
import com.github.cetonek.bigbiznis.domain.entity.persisted.refactored.BudgetBalance
import com.github.cetonek.bigbiznis.domain.repository.BudgetBalanceRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException


@DatabaseTest
class BudgetBalanceRepositoryTest @Autowired constructor(val repository: BudgetBalanceRepository) {

    @BeforeEach
    fun setUp() {
        repository.deleteAllInBatch()
    }

    @Test
    fun `test saving an entity`() {
        // given
        // when
        val entityToSave = BudgetBalance(year = 2030, millionsCrowns = 15)
        repository.save(entityToSave)
        // then
        val foundRate = repository.findById(entityToSave.id!!)
        assertThat(foundRate.isPresent).isTrue()
        assertThat(foundRate.get()).isEqualTo(entityToSave)
    }

    @Test
    fun `test saving an entity with 0 year wont work`() {
        // given
        // when
        // then
        assertThrows<DataIntegrityViolationException> {
            val entityToSave = BudgetBalance(year = 0, millionsCrowns = 15)
            repository.save(entityToSave)
        }
    }

}