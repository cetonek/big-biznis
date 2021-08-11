package com.github.cetonek.bigbiznis.application.web.mvc

import com.github.cetonek.bigbiznis.domain.service.OutputPercentageData
import com.github.cetonek.bigbiznis.application.utility.formatting.QuarterAndYear
import com.github.cetonek.bigbiznis.application.utility.formatting.percentage
import com.github.cetonek.bigbiznis.application.utility.model.Gdp
import com.github.cetonek.bigbiznis.application.utility.model.Home
import com.github.cetonek.bigbiznis.application.utility.model.Routing
import com.github.cetonek.bigbiznis.application.utility.utility.addBreadcrumbs
import com.github.cetonek.bigbiznis.application.utility.utility.mapToPairs
import com.github.cetonek.bigbiznis.application.utility.utility.quarterToRoman
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProductEntity
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProductType.REAL_2010_PRICES
import com.github.cetonek.bigbiznis.domain.service.FetchGrossDomesticProductUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(Routing.GDP)
class GrossDomesticProductController(private val fetchGdpUseCase: FetchGrossDomesticProductUseCase) {

    val template = "pages/gdp"

    @GetMapping
    fun getGdp(model: Model): String {
        model.addBreadcrumbs(Home, Gdp)

        model.addAttribute("realGdp2010Prices", fetchGdpUseCase.fetchGdp(REAL_2010_PRICES)
                .mapToPairs { Pair("${it.quarter.quarterToRoman()} ${it.year}", it.gdpMillionsCrowns) })

        val gdpChanges = fetchGdpUseCase.fetchPercentChangesPerQuarter(REAL_2010_PRICES)
        model.addAttribute("realGdpChanges", gdpChanges.mapToPairs {
            Pair("${it.dataPoint.quarter.quarterToRoman()} ${it.dataPoint.year}", it.value)
        })

        val current = gdpChanges.last()
        val currentTriple = getTriple("Aktuální HDP", current)

        val lowest = gdpChanges.minByOrNull { it.value }
                ?: throw IllegalStateException("lowest cannot be null but it is !")
        val lowestTriple = getTriple("Největší propad HDP", lowest)

        val highest = gdpChanges.maxByOrNull { it.value }
                ?: throw IllegalStateException("highest cannot be null but it is!")
        val highestTriple = getTriple("Nejvyšší růst HDP", highest)

        model.addAttribute("overviewItems", listOf(currentTriple, lowestTriple, highestTriple))

        return template
    }

    private fun getTriple(title: String, data: OutputPercentageData<GrossDomesticProductEntity>): Triple<*, *, *> {
        return Triple(
                title,
                QuarterAndYear(data.dataPoint.quarter, data.dataPoint.year),
                data.value.percentage)
    }


}