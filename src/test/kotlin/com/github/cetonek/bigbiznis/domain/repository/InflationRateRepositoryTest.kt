package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.DatabaseTest
import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationRate
import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationType
import com.github.cetonek.bigbiznis.domain.repository.InflationRateRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException

@DatabaseTest
class InflationRateRepositoryTest {

    @Autowired
    lateinit var repository: InflationRateRepository

    @BeforeEach
    fun setUp() {
        repository.deleteAllInBatch()
    }

    @Test
    fun `test saving an entity`() {
        // given
        // when
        val entityToSave = InflationRate(
                year = 2015,
                type = InflationType.THIS_MONTH_VS_PREVIOUS_YEARS_MONTH,
                inflationPercent = 4.5f,
                month = 10)
        repository.save(entityToSave)
        // then
        val foundRate = repository.findById(entityToSave.id!!)
        assertThat(foundRate.isPresent).isTrue()
        assertThat(foundRate.get()).isEqualTo(entityToSave)
    }

    @Test
    fun `test unique constraint `() {
        val firstEntity = InflationRate(
                year = 2015,
                type = InflationType.THIS_MONTH_VS_PREVIOUS_YEARS_MONTH,
                inflationPercent = 4.5f,
                month = 10)
        val savedEntity = repository.save(firstEntity)

        assertThrows<DataIntegrityViolationException> {
            repository.save(savedEntity.copy())
        }

    }


}