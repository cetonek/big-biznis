package com.github.cetonek.bigbiznis.integration

import com.github.cetonek.bigbiznis.integration.converter.CsvRootDto
import com.github.cetonek.bigbiznis.application.utility.date.DateFormatter
import com.github.cetonek.bigbiznis.application.utility.date.getQuarter
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProduct
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProductType
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import java.time.LocalDate
import kotlin.math.roundToLong

class GrossDomesticProductRootDto : CsvRootDto<GrossDomesticProductDto>()

class GrossDomesticProductDto {

    @JsonDeserialize(using = LocalDateDeserializer::class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.CNB_ARAD_RESPONSE_FORMAT)
    var date: LocalDate = LocalDate.now()

    var gdpBillionsCrowns: Double? = null

    val isValid: Boolean
        get() = gdpBillionsCrowns != null


}

fun GrossDomesticProductDto.toEntity(type: GrossDomesticProductType): GrossDomesticProduct {
    return GrossDomesticProduct(
            year = this.date.year,
            quarter = this.date.getQuarter(),
            type = type,
            gdpMillionsCrowns = (gdpBillionsCrowns!! * 1000).roundToLong()
    )
}