package com.github.cetonek.bigbiznis.unemploymentrate.data.database

import com.github.cetonek.bigbiznis.DatabaseTest
import com.github.cetonek.bigbiznis.unemploymentrate.domain.model.UnemploymentRatePerYearAvg
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@DatabaseTest
class UnemploymentRateRepositoryTest {

    @Autowired
    lateinit var repository: UnemploymentRateRepository

    @BeforeEach
    fun setUp() {
        repository.deleteAllInBatch()
    }

    @Test
    fun `test getting all yearly gdps`() {
        // given

        val firstQuater2016 = UnemploymentRateEntity(month = 1, year = 2016, unemploymentRatePercent = 5.0)
        val secondQuater2016 = UnemploymentRateEntity(month = 2, year = 2016, unemploymentRatePercent = 10.0)
        val thirdQuarter2016 = UnemploymentRateEntity(month = 3, year = 2016, unemploymentRatePercent = 15.0)

        val firstQuater2019 = UnemploymentRateEntity(month = 1, year = 2019, unemploymentRatePercent = 25.0)

        val thirdQuarter2020 = UnemploymentRateEntity(month = 3, year = 2020, unemploymentRatePercent = 50.0)
        val fourthQuarter2020 = UnemploymentRateEntity(month = 4, year = 2020, unemploymentRatePercent = 25.0)

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
        val firstQuater2016 = UnemploymentRateEntity(month = 1, year = 2016, unemploymentRatePercent = 5.0)
        val secondQuater2016 = UnemploymentRateEntity(month = 2, year = 2016, unemploymentRatePercent = 10.0)
        val thirdQuarter2016 = UnemploymentRateEntity(month = 3, year = 2016, unemploymentRatePercent = 15.0)

        val firstQuater2019 = UnemploymentRateEntity(month = 1, year = 2019, unemploymentRatePercent = 25.0)

        val thirdQuarter2020 = UnemploymentRateEntity(month = 3, year = 2020, unemploymentRatePercent = 50.0)
        val fourthQuarter2020 = UnemploymentRateEntity(month = 4, year = 2020, unemploymentRatePercent = 25.0)

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