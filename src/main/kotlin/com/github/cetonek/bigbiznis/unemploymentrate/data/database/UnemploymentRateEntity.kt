package com.github.cetonek.bigbiznis.unemploymentrate.data.database

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity(name = "unemployment_rate")
@IdClass(UnemploymentRateKey::class)
data class UnemploymentRateEntity(
        @Column(name = "month") @Id var month: Int = 0,
        @Column(name = "year") @Id var year: Int = 0,
        @Column(name = "value_percent") var unemploymentRatePercent: Double = 0.0
)

data class UnemploymentRateKey(
        var month: Int = 0,
        var year: Int = 0
) : Serializable

val UnemploymentRateEntity.key
        get() = UnemploymentRateKey(this.month, this.year)