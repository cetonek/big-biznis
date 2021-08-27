package com.github.cetonek.bigbiznis.domain.entity.persisted

import com.github.cetonek.bigbiznis.core.jpa.VersionedPersistableEntity
import com.github.cetonek.bigbiznis.domain.entity.ExchangeRate
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "exchange_rate")
class ExchangeRateEntity(

        @Column(name = "exchange_date")
        var date: LocalDate,

        var currencyCode: String,

        var currencyName: String,

        var amount: Int,

        var exchangeRate: BigDecimal,

        var country: String

) : VersionedPersistableEntity<Long>() {

    companion object {

        fun testInstance(date: LocalDate = LocalDate.now(),
                         currencyCode: String = "USD",
                         currencyName: String = "Dolar",
                         amount: Int = 1,
                         exchangeRate: BigDecimal = BigDecimal.ZERO,
                         country: String = "United States"
        ) = ExchangeRateEntity(date, currencyCode, currencyName, amount, exchangeRate, country)

    }

}

fun ExchangeRate.toEntity(): ExchangeRateEntity {
    return ExchangeRateEntity(
            date = this.date,
            currencyCode = this.currencyCode,
            currencyName = this.currencyName,
            amount = this.amount,
            exchangeRate = BigDecimal.valueOf(this.exchangeRate),
            country = this.country)
}

fun ExchangeRateEntity.toDomain(): ExchangeRate {
    return ExchangeRate(
            date = this.date,
            currencyCode = this.currencyCode,
            currencyName = this.currencyName,
            amount = this.amount,
            exchangeRate = this.exchangeRate.toDouble(),
            country = this.country)
}