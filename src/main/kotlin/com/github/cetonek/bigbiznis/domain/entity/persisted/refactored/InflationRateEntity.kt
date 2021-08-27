package com.github.cetonek.bigbiznis.domain.entity.persisted.refactored

import com.github.cetonek.bigbiznis.application.utility.utility.PairConvertable
import com.github.cetonek.bigbiznis.core.jpa.VersionedPersistableEntity
import javax.persistence.*

@Entity
@Table(name = "inflation_rate")
class InflationRateEntity(

        var month: Int,

        var year: Int,

        @Column(name = "inflation_type")
        @Enumerated(EnumType.STRING)
        var type: InflationType,

        var inflationPercent: Float

) : VersionedPersistableEntity<Long>(), PairConvertable {

    override fun convertToPair() = Pair(year, inflationPercent)

    fun copy(type: InflationType = this.type) =
            InflationRateEntity(month = this.month,
            year = this.year, type = type, inflationPercent = this.inflationPercent)

}

enum class InflationType {
    THIS_YEAR_VS_LAST_YEAR,
    THIS_MONTH_VS_PREVIOUS_YEARS_MONTH,
    THIS_MONTH_VS_PREVIOUS_MONTH
}

