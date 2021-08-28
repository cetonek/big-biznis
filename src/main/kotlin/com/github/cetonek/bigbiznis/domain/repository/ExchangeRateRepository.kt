package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.core.db.BigBiznisRepository
import com.github.cetonek.bigbiznis.domain.entity.LocalDateOnly
import com.github.cetonek.bigbiznis.domain.entity.persisted.ExchangeRate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Repository
interface ExchangeRateRepository : BigBiznisRepository<ExchangeRate, Long> {

    @Query(value = "SELECT * FROM exchange_rate WHERE exchange_date = (SELECT MAX(exchange_date) FROM exchange_rate)" +
            " ORDER BY country",
            nativeQuery = true)
    @Transactional(readOnly = true)
    fun findAllRatesFromLastDay(): Collection<ExchangeRate>


    @Query(value = "SELECT * FROM exchange_rate WHERE exchange_date = (SELECT MAX(exchange_date) FROM exchange_rate)" +
            "AND currency_code IN(?1) ORDER BY country",
            nativeQuery = true)
    @Transactional(readOnly = true)
    fun findAllRatesFromLastDayWhereCodeLike(currencyCodes: List<String>): Collection<ExchangeRate>

    @Query(value = "SELECT i\\:\\:date FROM generate_series( :startingDate, :endingDate, '1 day'\\:\\:interval) i " +
            "WHERE EXTRACT (ISODOW FROM i) IN (1,2,3,4,5) " +
            "EXCEPT (SELECT exchange_date FROM exchange_rate) ORDER BY i",
            nativeQuery = true)
    @Transactional(readOnly = true)
    fun findAllWeekDaysThatAreMissing(@Param("startingDate") startingDate: LocalDate,
                                      @Param("endingDate") endingDate: LocalDate = LocalDate.now()): Collection<LocalDateOnly>

    @Transactional(readOnly = true)
    fun findFirstByOrderByDate(): ExchangeRate

    @Transactional(readOnly = true)
    fun findExchangeRateEntityByCurrencyCodeOrderByDate(currencyCode: String): List<ExchangeRate>

    @Transactional(readOnly = true)
    fun findByDateAndCurrencyCode(date: LocalDate, currencyCode: String): ExchangeRate?

    @Transactional
    fun updateOrCreateEntity(entity: ExchangeRate): ExchangeRate {
        val foundEntity = findByDateAndCurrencyCode(date = entity.date, currencyCode = entity.currencyCode)
        return if (foundEntity == null) {
            save(entity)
        } else {
            foundEntity.updateByOther(entity)
        }
    }


}