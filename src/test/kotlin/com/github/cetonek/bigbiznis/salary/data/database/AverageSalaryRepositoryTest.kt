package com.github.cetonek.bigbiznis.salary.data.database

import com.github.cetonek.bigbiznis.DatabaseTest
import com.github.cetonek.bigbiznis.domain.entity.persisted.refactored.AverageSalary
import com.github.cetonek.bigbiznis.domain.repository.AverageSalaryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@DatabaseTest
class AverageSalaryRepositoryTest {

    @Autowired
    lateinit var repository: AverageSalaryRepository

    @BeforeEach
    fun setUp() {
        repository.deleteAllInBatch()
    }

    @Test
    fun `test saving an entity`() {
        // given
        // when
        val entityToSave = AverageSalary(year = 2015, quarter = 1, crowns = 28547)
        repository.save(entityToSave)
        // then
        val foundRate = repository.findById(entityToSave.id!!)
        assertThat(foundRate.isPresent).isTrue()
        assertThat(foundRate.get()).isEqualTo(entityToSave)
    }


}