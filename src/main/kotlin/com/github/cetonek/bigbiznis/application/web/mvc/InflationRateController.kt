package com.github.cetonek.bigbiznis.application.web.mvc

import com.github.cetonek.bigbiznis.application.utility.formatting.MonthAndYear
import com.github.cetonek.bigbiznis.application.utility.formatting.MonthAndYearConverter
import com.github.cetonek.bigbiznis.application.utility.formatting.percentage
import com.github.cetonek.bigbiznis.application.utility.model.Home
import com.github.cetonek.bigbiznis.application.utility.model.Inflation
import com.github.cetonek.bigbiznis.application.utility.model.OverviewAndGraph
import com.github.cetonek.bigbiznis.application.utility.model.Routing
import com.github.cetonek.bigbiznis.application.utility.utility.addBreadcrumbs
import com.github.cetonek.bigbiznis.application.utility.utility.mapToPairs
import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationRateEntity
import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationType
import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationType.*
import com.github.cetonek.bigbiznis.domain.service.FetchInflationRateUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(Routing.INFLATION)
class InflationRateController(private val fetchInflation: FetchInflationRateUseCase,
                              private val monthAndYear: MonthAndYearConverter) {

    val template = "pages/inflation"

    @GetMapping
    fun getInflation(model: Model): String {
        model.addBreadcrumbs(Home, Inflation)

        model.addSection("averageYearlyInflation", THIS_YEAR_VS_LAST_YEAR)
        model.addSection("thisMonthVsPrevYearsMonthsInflation", THIS_MONTH_VS_PREVIOUS_YEARS_MONTH)
        model.addSection("monthlyInflation", THIS_MONTH_VS_PREVIOUS_MONTH)

        return template
    }

    private fun Model.addSection(attributeName: String, type: InflationType) {
        val inflation = fetchInflation.fetchAllInflationRatesByType(type)
        this.addAttribute(attributeName,
                OverviewAndGraph(overview(inflation), formatGraphItems(inflation)))
    }

    private fun formatGraphItems(input: List<InflationRateEntity>): List<Pair<Any, Any>> {
        return input.mapToPairs {
            Pair(monthAndYear.convert(MonthAndYear(it.month, it.year)), it.valuePercent)
        }
    }

    private fun overview(input: List<InflationRateEntity>): List<Triple<*, *, *>> {
        return listOf(
                triple("Aktuální", input.last()),
                triple("Nejnižší", input.minByOrNull { it.valuePercent }!!),
                triple("Nejvyšší", input.maxByOrNull { it.valuePercent }!!)
        )
    }

    private fun triple(title: String, inflationRate: InflationRateEntity): Triple<*, *, *> {
        return Triple(title,
                MonthAndYear(inflationRate.month, inflationRate.year),
                inflationRate.valuePercent.percentage
        )
    }

}