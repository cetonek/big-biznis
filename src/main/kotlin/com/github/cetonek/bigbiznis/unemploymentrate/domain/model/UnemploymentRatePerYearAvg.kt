package com.github.cetonek.bigbiznis.unemploymentrate.domain.model

import com.github.cetonek.bigbiznis.core.presentation.utility.PairConvertable

data class UnemploymentRatePerYearAvg(val year: Int = 0,
                                      val unemploymentRatePercent: Double = 0.0) : PairConvertable {
    override fun convertToPair() = Pair(year, unemploymentRatePercent)
}