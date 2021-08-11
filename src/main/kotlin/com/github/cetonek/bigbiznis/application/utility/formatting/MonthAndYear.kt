package com.github.cetonek.bigbiznis.application.utility.formatting

import com.github.cetonek.bigbiznis.application.utility.date.translate
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.Month

data class MonthAndYear(val month: Int,
                        val year: Int)

@Component
class MonthAndYearConverter : Converter<MonthAndYear, String> {
    override fun convert(input: MonthAndYear) = "${Month.of(input.month).translate()} ${input.year}"
}