package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.DatabaseTest
import com.github.cetonek.bigbiznis.domain.entity.persisted.ExchangeRate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.DayOfWeek
import java.time.LocalDate

@DatabaseTest
class ExchangeRateRepositoryTest {

    @Autowired
    lateinit var repository: ExchangeRateRepository

    final val monday = LocalDate.of(2021, 8, 23)
    val tuesday = monday.plusDays(1)
    val wednesday = monday.plusDays(2)
    val thrursday = monday.plusDays(3)
    val friday = monday.plusDays(4)
    val saturday = monday.plusDays(5)
    val sunday = monday.plusDays(6)

    @BeforeEach
    fun setUp() {
        repository.deleteAllInBatch()
        assertThat(monday.dayOfWeek).isEqualTo(DayOfWeek.MONDAY)
    }

    @Test
    fun `entity is saved with correct date`() {
        // given
        val today = LocalDate.of(2020, 1, 18)
        val usdEntity = ExchangeRate(date = today,
                currencyCode = "USD", exchangeRate = 22.5.toBigDecimal(),
                amount = 1, country = "United States", currencyName = "Dolar"
        )
        // when
        val saved = repository.save(usdEntity)
        assertThat(saved).isEqualTo(usdEntity)
        // then
        assertThat(repository.findById(usdEntity.id!!).get()).isEqualTo(usdEntity)
    }

    @Test
    fun `test retrieve all exchange rates from last day and with given code`() {
        // given
        val today = LocalDate.now()
        val yesterday = today.minusDays(1)

        val usdToday = ExchangeRate.testInstance(date = today, currencyCode = "USD", exchangeRate = 22.5.toBigDecimal())
        val usdYesterday = ExchangeRate.testInstance(date = yesterday, currencyCode = "USD", exchangeRate = 20.5.toBigDecimal())

        val eurToday = ExchangeRate.testInstance(date = today, currencyCode = "EUR", exchangeRate = 22.5.toBigDecimal())
        val eurYesterday = ExchangeRate.testInstance(date = yesterday, currencyCode = "EUR", exchangeRate = 28.5.toBigDecimal())

        repository.saveAll(
                listOf(usdToday, usdYesterday,
                        eurToday, eurYesterday))

        // when
        val result = repository.findAllRatesFromLastDayWhereCodeLike(listOf("USD"))
        // then
        assertThat(result.size).isEqualTo(1)
        assertThat(result.first()).isEqualTo(usdToday)
    }

    @Test
    fun `repository finds weekdays that are not in database - empty database`() {
        repository.deleteAllInBatch()

        assertThat(monday.dayOfWeek).isEqualTo(DayOfWeek.MONDAY)

        val result = repository.findAllWeekDaysThatAreMissing(monday, sunday)

        assertThat(result.size).isEqualTo(5)
        assertThat(result.map { it.i }).containsExactlyInAnyOrder(monday, tuesday, wednesday, thrursday, friday)
    }

    @Test
    fun `repository finds weekdays that are not in database - with days in db`() {
        repository.saveAll(listOf(
                monday, tuesday, friday, saturday, sunday)
                .map { ExchangeRate.testInstance(date = it) }
        )

        val result = repository.findAllWeekDaysThatAreMissing(monday, sunday)

        assertThat(result.size).isEqualTo(2)
        assertThat(result.map { it.i }).containsExactlyInAnyOrder(wednesday, thrursday)
    }

    @Test
    fun `repository finds weekdays that are not in database - with days in db and range from friday to sunday`() {
        repository.saveAll(listOf(
                monday, tuesday, wednesday, saturday)
                .map { ExchangeRate.testInstance(date = it) }
        )

        val result = repository.findAllWeekDaysThatAreMissing(friday, sunday)

        assertThat(result.size).isEqualTo(1)
        assertThat(result.map { it.i }).containsExactlyInAnyOrder(friday)
    }

}
