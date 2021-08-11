package com.github.cetonek.bigbiznis.domain.entity.persisted

import com.github.cetonek.bigbiznis.application.utility.utility.PairConvertable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id


@Entity(name = "budget_balance")
data class BudgetBalanceEntity(
        @Column(name = "year") @Id val year: Int = 0,
        @Column(name = "value_millions_crowns") val millionsCrowns: Long = 0
) : PairConvertable {
    override fun convertToPair() = Pair(year, millionsCrowns)
}