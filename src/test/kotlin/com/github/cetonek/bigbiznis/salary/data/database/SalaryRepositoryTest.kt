package com.github.cetonek.bigbiznis.salary.data.database

import com.github.cetonek.bigbiznis.DatabaseTest
import com.github.cetonek.bigbiznis.domain.entity.persisted.SalaryEntity
import com.github.cetonek.bigbiznis.domain.entity.persisted.key
import com.github.cetonek.bigbiznis.domain.repository.SalaryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@DatabaseTest
class SalaryRepositoryTest {

    @Autowired
    lateinit var repository: SalaryRepository

    @BeforeEach
    fun setUp() {
        repository.deleteAllInBatch()
    }

    @Test
    fun `test saving an entity`() {
        // given
        // when
        val entityToSave = SalaryEntity(year = 2015, quarter = 1, salaryCrowns = 28547)
        repository.save(entityToSave)
        // then
        val foundRate = repository.findById(entityToSave.key)
        assertThat(foundRate.isPresent).isTrue()
        assertThat(foundRate.get()).isEqualTo(entityToSave)
    }


}