package com.github.cetonek.bigbiznis.domain.entity.persisted

import com.github.cetonek.bigbiznis.application.utility.utility.PairConvertable
import com.github.cetonek.bigbiznis.core.jpa.VersionedPersistableEntity
import javax.persistence.*

@Entity
@Table(name = "gross_domestic_product")
class GrossDomesticProductEntity(

        var year: Int,

        var quarter: Int,

        @Column(name = "gdp_type")
        @Enumerated(EnumType.STRING)
        var type: GrossDomesticProductType = GrossDomesticProductType.NOMINAL,

        var gdpMillionsCrowns: Long

) : VersionedPersistableEntity<Long>(), PairConvertable {

    override fun convertToPair(): Pair<Any, Any> {
        return Pair(year, gdpMillionsCrowns)
    }

    fun copy(quarter: Int) = GrossDomesticProductEntity(year = this.year,
            quarter = quarter, type = this.type, gdpMillionsCrowns = this.gdpMillionsCrowns)

    fun copy(quarter: Int, year: Int) = GrossDomesticProductEntity(year = year,
            quarter = quarter, type = this.type, gdpMillionsCrowns = this.gdpMillionsCrowns)


}

enum class GrossDomesticProductType {
    NOMINAL,
    REAL_2010_PRICES
}

