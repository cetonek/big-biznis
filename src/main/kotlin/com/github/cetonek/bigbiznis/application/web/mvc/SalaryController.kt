package com.github.cetonek.bigbiznis.application.web.mvc

import com.github.cetonek.bigbiznis.application.utility.formatting.QuarterAndYear
import com.github.cetonek.bigbiznis.application.utility.formatting.QuarterAndYearConverter
import com.github.cetonek.bigbiznis.application.utility.formatting.czechCrowns
import com.github.cetonek.bigbiznis.application.utility.model.Home
import com.github.cetonek.bigbiznis.application.utility.model.OverviewAndGraph
import com.github.cetonek.bigbiznis.application.utility.model.Routing
import com.github.cetonek.bigbiznis.application.utility.model.Salary
import com.github.cetonek.bigbiznis.application.utility.utility.addBreadcrumbs
import com.github.cetonek.bigbiznis.application.utility.utility.mapToPairs
import com.github.cetonek.bigbiznis.domain.entity.persisted.SalaryEntity
import com.github.cetonek.bigbiznis.domain.service.FetchSalaryUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(Routing.SALARY)
class SalaryController(private val fetchSalary: FetchSalaryUseCase,
                       private val quarterAndYearConverter: QuarterAndYearConverter) {

    val template = "pages/salary"

    @GetMapping
    fun getSalary(model: Model): String {
        model.addBreadcrumbs(Home, Salary)

        val salary = fetchSalary.fetchAll()

        model.addAttribute("salary", OverviewAndGraph(
                graph = salary.mapToPairs {
                    Pair(quarterAndYearConverter.convert(QuarterAndYear(it.quarter, it.year)),
                            it.salaryCrowns)
                },
                overview = overview(salary)
        ))

        return template
    }

    private fun overview(input: List<SalaryEntity>): List<Triple<*, *, *>> {
        return listOf(
                triple("Aktuální", input.last()),
                triple("Nejnižší", input.minByOrNull { it.salaryCrowns }!!),
                triple("Nejvyšší", input.maxByOrNull { it.salaryCrowns }!!)
        )
    }

    private fun triple(title: String, salary: SalaryEntity): Triple<*, *, *> {
        return Triple(title,
                QuarterAndYear(salary.quarter, salary.year),
                salary.salaryCrowns.czechCrowns
        )
    }

}