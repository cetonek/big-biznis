package com.github.cetonek.bigbiznis.application.web.mvc

import com.github.cetonek.bigbiznis.application.utility.formatting.MonthAndYear
import com.github.cetonek.bigbiznis.application.utility.formatting.MonthAndYearConverter
import com.github.cetonek.bigbiznis.application.utility.formatting.percentage
import com.github.cetonek.bigbiznis.application.utility.model.Home
import com.github.cetonek.bigbiznis.application.utility.model.OverviewAndGraph
import com.github.cetonek.bigbiznis.application.utility.model.Routing
import com.github.cetonek.bigbiznis.application.utility.model.Unemployment
import com.github.cetonek.bigbiznis.application.utility.utility.addBreadcrumbs
import com.github.cetonek.bigbiznis.application.utility.utility.mapToPairs
import com.github.cetonek.bigbiznis.domain.entity.persisted.UnemploymentRate
import com.github.cetonek.bigbiznis.domain.service.FetchUnemploymentRateUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(Routing.UNEMPLOYMENT)
class UnemploymentController(private val fetchUnemp: FetchUnemploymentRateUseCase,
                             private val monthAndYearConverter: MonthAndYearConverter) {

    val template = "pages/unemployment"

    @GetMapping
    fun getUnemployment(model: Model): String {
        model.addBreadcrumbs(Home, Unemployment)

        val unemployment = fetchUnemp.fetchAllMonthlyUnempRates()

        model.addAttribute("unemployment", OverviewAndGraph(
                graph = unemployment.mapToPairs {
                    Pair(monthAndYearConverter.convert(MonthAndYear(it.month, it.year)),
                            it.unemploymentPercent)
                },
                overview = overview(unemployment)
        ))

        return template
    }

    private fun overview(input: List<UnemploymentRate>): List<Triple<*, *, *>> {
        return listOf(
                triple("Aktuální", input.last()),
                triple("Nejnižší", input.minByOrNull { it.unemploymentPercent }!!),
                triple("Nejvyšší", input.maxByOrNull { it.unemploymentPercent }!!)
        )
    }

    private fun triple(title: String, unemployment: UnemploymentRate): Triple<*, *, *> {
        return Triple(title,
                MonthAndYear(unemployment.month, unemployment.year),
                unemployment.unemploymentPercent.percentage
        )
    }


}