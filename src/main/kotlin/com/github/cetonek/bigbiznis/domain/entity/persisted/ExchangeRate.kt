package com.github.cetonek.bigbiznis.domain.entity.persisted

import com.github.cetonek.bigbiznis.core.jpa.VersionedPersistableEntity
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "exchange_rate")
class ExchangeRate(

        @Column(name = "exchange_date")
        var date: LocalDate,

        var currencyCode: String,

        var currencyName: String,

        var amount: Int,

        var exchangeRate: BigDecimal,

        var country: String

) : VersionedPersistableEntity<Long>() {

    fun updateByOther(other: ExchangeRate) : ExchangeRate {
        currencyName = other.currencyName
        amount = other.amount
        exchangeRate = other.exchangeRate
        country = other.country
        return this
    }

    companion object {

        fun testInstance(date: LocalDate = LocalDate.now(),
                         currencyCode: String = "USD",
                         currencyName: String = "Dolar",
                         amount: Int = 1,
                         exchangeRate: BigDecimal = BigDecimal.ZERO,
                         country: String = "United States"
        ) = ExchangeRate(date, currencyCode, currencyName, amount, exchangeRate, country)

    }

}
