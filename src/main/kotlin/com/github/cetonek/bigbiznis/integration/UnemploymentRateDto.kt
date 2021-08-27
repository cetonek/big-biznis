package com.github.cetonek.bigbiznis.integration

import com.github.cetonek.bigbiznis.integration.converter.CsvRootDto
import com.github.cetonek.bigbiznis.application.utility.date.DateFormatter
import com.github.cetonek.bigbiznis.domain.entity.persisted.UnemploymentRateEntity
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import java.time.LocalDate

class UnemploymentRateRootDto : CsvRootDto<UnemploymentRateDto>()

class UnemploymentRateDto {

    @JsonDeserialize(using = LocalDateDeserializer::class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.CNB_ARAD_RESPONSE_FORMAT)
    var date: LocalDate = LocalDate.now()

    var unemploymentRate: Double? = null

    val isValid: Boolean
        get() = unemploymentRate != null

}

fun UnemploymentRateDto.toEntity(): UnemploymentRateEntity {
    return UnemploymentRateEntity(
            year = this.date.year,
            month = this.date.month.value,
            unemploymentPercent = (this.unemploymentRate
                    ?: throw IllegalStateException("unemployment shouldnt be null at this point but it is!")).toFloat())
}