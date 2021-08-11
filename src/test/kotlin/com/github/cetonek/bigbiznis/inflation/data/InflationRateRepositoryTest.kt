package com.github.cetonek.bigbiznis.inflation.data

import com.github.cetonek.bigbiznis.DatabaseTest
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

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
        val entityToSave = InflationRateEntity(
                year = 2015,
                type = InflationType.THIS_MONTH_VS_PREVIOUS_YEARS_MONTH,
                valuePercent = 4.5f,
                month = 10)
        repository.save(entityToSave)
        // then
        val foundRate = repository.findById(entityToSave.key)
        assertThat(foundRate.isPresent).isTrue()
        assertThat(foundRate.get()).isEqualTo(entityToSave)
    }


}