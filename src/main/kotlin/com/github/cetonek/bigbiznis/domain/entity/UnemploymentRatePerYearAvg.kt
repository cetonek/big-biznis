package com.github.cetonek.bigbiznis.domain.entity

import com.github.cetonek.bigbiznis.application.utility.utility.PairConvertable

data class UnemploymentRatePerYearAvg(val year: Int = 0,
                                      val unemploymentRatePercent: Double = 0.0) : PairConvertable {
    override fun convertToPair() = Pair(year, unemploymentRatePercent)
}