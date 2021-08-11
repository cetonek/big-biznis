package com.github.cetonek.bigbiznis.salary.data.api

import com.github.cetonek.bigbiznis.core.data.api.converter.CsvRootDto
import com.github.cetonek.bigbiznis.core.presentation.date.DateFormatter
import com.github.cetonek.bigbiznis.core.presentation.date.getQuarter
import com.github.cetonek.bigbiznis.salary.data.database.SalaryEntity
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import java.time.LocalDate

class SalaryDto {

    @JsonDeserialize(using = LocalDateDeserializer::class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.CNB_ARAD_RESPONSE_FORMAT)
    var date: LocalDate = LocalDate.now()

    var salaryCrowns: Int? = null

    override fun toString(): String {
        return "SalaryDto(date=$date, salaryCrowns=$salaryCrowns)"
    }

    val isValid: Boolean
        get() = salaryCrowns != null

}

class SallaryRootDto : CsvRootDto<SalaryDto>()

fun SalaryDto.toEntity(): SalaryEntity {
    return SalaryEntity(
            quarter = this.date.getQuarter(),
            year = this.date.year,
            salaryCrowns = this.salaryCrowns
                    ?: throw IllegalStateException("salary cannot be null at this point but it is null!"))
}