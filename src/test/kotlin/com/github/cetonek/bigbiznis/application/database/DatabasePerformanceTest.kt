package com.github.cetonek.bigbiznis.application.database

import com.github.cetonek.bigbiznis.DatabaseTest
import com.github.cetonek.bigbiznis.domain.entity.persisted.ExchangeRate
import com.github.cetonek.bigbiznis.domain.repository.ExchangeRateRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
@Disabled
class DatabasePerformanceTest {

    @Autowired
    lateinit var repository: ExchangeRateRepository

    @BeforeEach
    fun setUp() {
        repository.deleteAllInBatch()
    }

    @Test
    fun `test insert speed of 10 000 items`() {
        // given
        val numberOfItems = 10_000
        val items = generateItems(numberOfItems)
        // when
        val insertStart = System.currentTimeMillis()
        repository.saveAll(items)
        // then
        println("inserted $numberOfItems items in: ${System.currentTimeMillis() - insertStart} millis")
    }

    @Test
    fun `test insert speed of 100 000 items`() {
        // given
        val numberOfItems = 100_000
        val items = generateItems(numberOfItems)
        // when
        val insertStart = System.currentTimeMillis()
        repository.saveAll(items)
        // then
        println("inserted $numberOfItems items in: ${System.currentTimeMillis() - insertStart} millis")
    }

    @Test
    fun `test insert speed of 1 000 000 items`() {
        // given
        val numberOfItems = 1_000_000
        val items = generateItems(numberOfItems)
        // when
        val insertStart = System.currentTimeMillis()
        repository.saveAll(items)
        // then
        println("inserted $numberOfItems items in: ${System.currentTimeMillis() - insertStart} millis")
    }

    @Test
    fun `test delete speed of 1_000_000 items in batch`() {
        // given
        val numberOfItems = 1_000_000
        val items = generateItems(numberOfItems)
        repository.saveAll(items)
        // when
        val deleteStart = System.currentTimeMillis()
        repository.deleteAllInBatch()
        // then
        println("deleted $numberOfItems items in batch in: ${System.currentTimeMillis() - deleteStart} millis")
    }


    private fun generateItems(itemsCount: Int): List<ExchangeRate> {
        val startingDate = LocalDate.of(1900, 1, 1)
        val entity = ExchangeRate.testInstance(
                startingDate,
                "USD",
                "dolar",
                1,
                15.57.toBigDecimal(),
                "USA")

        return (0 until itemsCount)
                .map {
                    ExchangeRate.testInstance(date = entity.date.plusDays(it.toLong()))
                }
                .toList()

    }
}