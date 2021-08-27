package com.github.cetonek.bigbiznis.unemploymentrate.data.database

import com.github.cetonek.bigbiznis.DatabaseTest
import com.github.cetonek.bigbiznis.domain.entity.persisted.UnemploymentRate
import com.github.cetonek.bigbiznis.domain.repository.UnemploymentRateRepository
import com.github.cetonek.bigbiznis.domain.entity.UnemploymentRatePerYearAvg
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException

@DatabaseTest
class UnemploymentRateRepositoryTest {

    @Autowired
    lateinit var repository: UnemploymentRateRepository

    @BeforeEach
    fun setUp() {
        repository.deleteAllInBatch()
    }

    @Test
    fun `entity can be saved and retrieved`() {
        val savedEntity = repository.save(UnemploymentRate(2020, 12, 10f))

        val foundEntity = repository.findById(savedEntity.id!!)

        assertThat(foundEntity).isNotNull
    }

    @Test
    fun `entity with negative unemployment cannot be saved`() {
        assertThrows<DataIntegrityViolationException> {
            repository.save(UnemploymentRate(2020, 12, -10f))
        }
    }

    @Test
    fun `test getting yearly averaged unemployment`() {
        // given

        val firstQuater2016 = UnemploymentRate(year = 2016, month = 1, unemploymentPercent = 5.0F)
        val secondQuater2016 = UnemploymentRate(year = 2016, month = 2, unemploymentPercent = 10.0f)
        val thirdQuarter2016 = UnemploymentRate(year = 2016, month = 3, unemploymentPercent = 15.0f)

        val firstQuater2019 = UnemploymentRate(year = 2019, month = 1, unemploymentPercent = 25.0f)

        val thirdQuarter2020 = UnemploymentRate(year = 2020, month = 3, unemploymentPercent = 50.0f)
        val fourthQuarter2020 = UnemploymentRate(year = 2020, month = 4, unemploymentPercent = 25.0f)

        repository.saveAll(listOf(
                firstQuater2016, secondQuater2016, thirdQuarter2016,
                firstQuater2019,
                thirdQuarter2020, fourthQuarter2020))
        // when
        val result = repository.getAllYearlyAveragedUnemploymentRates()
        // then

        print("\n\n\n RESULT:")
        print(result)
        print("\n\n\n")

        assertThat(result.size).isEqualTo(3)

        assertThat(result.first()).isEqualTo(UnemploymentRatePerYearAvg(2016, 10.0))

        assertThat(result[1]).isEqualTo(UnemploymentRatePerYearAvg(2019, 25.0))

        assertThat(result[2]).isEqualTo(UnemploymentRatePerYearAvg(2020, 37.5))

    }

    @Test
    fun `test getting latest unemployment`() {
        // given
        val firstQuater2016 = UnemploymentRate(year = 2016, month = 1, unemploymentPercent = 5.0f)
        val secondQuater2016 = UnemploymentRate(year = 2016, month = 2, unemploymentPercent = 10.0f)
        val thirdQuarter2016 = UnemploymentRate(year = 2016, month = 3, unemploymentPercent = 15.0f)

        val firstQuater2019 = UnemploymentRate(year = 2019, month = 1, unemploymentPercent = 25.0f)

        val thirdQuarter2020 = UnemploymentRate(year = 2020, month = 3, unemploymentPercent = 50.0f)
        val fourthQuarter2020 = UnemploymentRate(year = 2020, month = 4, unemploymentPercent = 25.0f)

        repository.saveAll(listOf(
                firstQuater2016, secondQuater2016, thirdQuarter2016,
                firstQuater2019,
                thirdQuarter2020, fourthQuarter2020))
        // when
        val result = repository.findFirstByOrderByYearDescMonthDesc()
        // then

        print("\n\n\n RESULT:")
        print(result)
        print("\n\n\n")

        assertThat(result).isEqualTo(fourthQuarter2020)

    }

}