package com.github.cetonek.bigbiznis.domain.entity.persisted

import com.github.cetonek.bigbiznis.application.utility.utility.PairConvertable
import com.github.cetonek.bigbiznis.core.jpa.VersionedPersistableEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "public_debt")
class PublicDebt(
        val year: Int,

        @Column(name = "debt_millions_crowns")
        val millionsCrowns: Long
        
) : VersionedPersistableEntity<Long>(), PairConvertable {

    override fun convertToPair() = Pair(year, millionsCrowns)

}