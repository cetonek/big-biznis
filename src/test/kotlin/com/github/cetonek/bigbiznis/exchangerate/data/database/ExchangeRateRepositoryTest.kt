package com.github.cetonek.bigbiznis.exchangerate.data.database

import com.github.cetonek.bigbiznis.TestProfile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDate

@DataJpaTest
@TestProfile
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ExchangeRateRepositoryTest {

    @Autowired
    lateinit var repository: ExchangeRateRepository

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Test
    fun `test saving an entity`() {
        // given
        // when
        val entityToSave = ExchangeRateEntity()
        repository.save(entityToSave)
        // then
        val foundRate = repository.findById(entityToSave.key)
        assertThat(foundRate.isPresent).isTrue()
        assertThat(foundRate.get()).isEqualTo(entityToSave)
    }

    @Test
    fun `fetch all exchange rates for latest available day`() {
        // given
        repository.deleteAllInBatch()

        val today = LocalDate.now()
        val yesteday = today.minusDays(1)

        // prepare db data
        val todaysUSDrate = ExchangeRateEntity(date = today, currencyCode = "USD", exchangeRate = 22.5)
        val todaysCADrate = todaysUSDrate.copy(currencyCode = "CAD", exchangeRate = 24.5)
        val yesterdaysUSDrate = todaysUSDrate.copy(date = yesteday, exchangeRate = 23.5)
        val yesterdaysCADrate = todaysCADrate.copy(date = yesteday)
        // populate db with data
        with(entityManager) {
            persistAndFlush(todaysUSDrate)
            persistAndFlush(todaysCADrate)
            persistAndFlush(yesterdaysUSDrate)
            persistAndFlush(yesterdaysCADrate)
        }

        // when
        val resultRates = repository.findAllRatesFromLastDay()
        // then
        assertThat(resultRates.size).isEqualTo(2)
        assertThat(resultRates).containsOnly(todaysCADrate, todaysUSDrate)
    }

    @Test
    fun `saving same entity twice overwrites given record`() {
        // given
        val today = LocalDate.now()
        val oldUSD = ExchangeRateEntity(date = today, currencyCode = "USD", exchangeRate = 22.5)
        val newUSD = oldUSD.copy(exchangeRate = 25.5)
        // when
        repository.save(oldUSD)
        repository.save(newUSD)
        // then
        val foundOptional = repository.findById(oldUSD.key)
        assertThat(foundOptional).isPresent
        assertThat(foundOptional.get()).isEqualTo(newUSD)
    }
}