package com.github.cetonek.bigbiznis.core.presentation.formatting

import com.github.cetonek.bigbiznis.core.presentation.utility.quarterToRoman
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

data class QuarterAndYear(val quarter: Int,
                          val year: Int)

@Component
class QuarterAndYearConverter : Converter<QuarterAndYear, String> {

    override fun convert(input: QuarterAndYear): String {
        return "${input.quarter.quarterToRoman()} ${input.year}"
    }

}