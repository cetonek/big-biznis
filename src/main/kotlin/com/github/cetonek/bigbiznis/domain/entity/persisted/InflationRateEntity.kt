package com.github.cetonek.bigbiznis.domain.entity.persisted

import com.github.cetonek.bigbiznis.application.utility.utility.PairConvertable
import java.io.Serializable
import javax.persistence.*

@Entity(name = "inflation_rate")
@IdClass(InflationRateKey::class)
data class InflationRateEntity(
        @Column(name = "month") @Id var month: Int = 0,
        @Column(name = "year") @Id var year: Int = 0,
        @Column(name = "type") @Enumerated(EnumType.STRING) @Id var type: InflationType = InflationType.THIS_YEAR_VS_LAST_YEAR,
        @Column(name = "value_percent") var valuePercent: Float = 0f
) : PairConvertable {
    override fun convertToPair() = Pair(year, valuePercent)
}

enum class InflationType {
    THIS_YEAR_VS_LAST_YEAR,
    THIS_MONTH_VS_PREVIOUS_YEARS_MONTH,
    THIS_MONTH_VS_PREVIOUS_MONTH
}

data class InflationRateKey(
        var month: Int = 0,
        var year: Int = 0,
        var type: InflationType = InflationType.THIS_YEAR_VS_LAST_YEAR
) : Serializable

val InflationRateEntity.key
    get() = InflationRateKey(this.month, this.year, this.type)