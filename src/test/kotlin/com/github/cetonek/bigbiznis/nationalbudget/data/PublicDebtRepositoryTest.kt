package com.github.cetonek.bigbiznis.nationalbudget.data

import com.github.cetonek.bigbiznis.DatabaseTest
import com.github.cetonek.bigbiznis.domain.entity.persisted.PublicDebt
import com.github.cetonek.bigbiznis.domain.repository.PublicDebtRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@DatabaseTest
class PublicDebtRepositoryTest {

    @Autowired
    lateinit var repository: PublicDebtRepository

    @BeforeEach
    fun setUp() {
        repository.deleteAllInBatch()
    }

    @Test
    fun `test saving an entity`() {
        // given
        // when
        val entityToSave = PublicDebt(year = 2015, millionsCrowns = 446545)
        val savedEntity = repository.save(entityToSave)
        // then
        val foundEntity = repository.findById(savedEntity.id!!)
        assertThat(foundEntity.isPresent).isTrue
        assertThat(foundEntity.get()).isEqualTo(entityToSave)
    }


}