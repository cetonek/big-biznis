package com.github.cetonek.bigbiznis.domain.entity.persisted

import com.github.cetonek.bigbiznis.application.utility.utility.PairConvertable
import com.github.cetonek.bigbiznis.core.jpa.VersionedPersistableEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "budget_balance")
class BudgetBalance(

        var year: Int,

        @Column(name = "balance_millions_crowns") var millionsCrowns: Long

) : VersionedPersistableEntity<Long>(), PairConvertable {

    override fun convertToPair() = Pair(year, millionsCrowns)

}