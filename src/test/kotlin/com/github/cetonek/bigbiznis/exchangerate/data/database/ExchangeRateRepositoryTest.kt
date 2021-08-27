package com.github.cetonek.bigbiznis.exchangerate.data.database

import com.github.cetonek.bigbiznis.DatabaseTest
import com.github.cetonek.bigbiznis.domain.entity.persisted.ExchangeRateEntity
import com.github.cetonek.bigbiznis.domain.repository.ExchangeRateRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

@DatabaseTest
class ExchangeRateRepositoryTest {

    @Autowired
    lateinit var repository: ExchangeRateRepository

    @BeforeEach
    fun setUp() {
        repository.deleteAllInBatch()
    }


    @Test
    fun `entity is saved with correct date`() {
        // given
        val today = LocalDate.of(2020, 1, 18)
        val usdEntity = ExchangeRateEntity(date = today,
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

        val usdToday = ExchangeRateEntity.testInstance(date = today, currencyCode = "USD", exchangeRate = 22.5.toBigDecimal())
        val usdYesterday = ExchangeRateEntity.testInstance(date = yesterday, currencyCode = "USD", exchangeRate = 20.5.toBigDecimal())

        val eurToday = ExchangeRateEntity.testInstance(date = today, currencyCode = "EUR", exchangeRate = 22.5.toBigDecimal())
        val eurYesterday = ExchangeRateEntity.testInstance(date = yesterday, currencyCode = "EUR", exchangeRate = 28.5.toBigDecimal())

        repository.saveAll(
                listOf(usdToday, usdYesterday,
                        eurToday, eurYesterday))

        // when
        val result = repository.findAllRatesFromLastDayWhereCodeLike(listOf("USD"))
        // then
        assertThat(result.size).isEqualTo(1)
        assertThat(result.first()).isEqualTo(usdToday)
    }

}
