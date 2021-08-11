package com.github.cetonek.bigbiznis.application.utility.model

open class BreadcrumbItem(val name: String,
                          val link: String = Routing.DASHBOARD)

data class Breadcrumbs(val items: Collection<BreadcrumbItem>)

object Home : BreadcrumbItem("Domů")

object Gdp : BreadcrumbItem("HDP", Routing.GDP)

object Inflation : BreadcrumbItem("Inflace", Routing.INFLATION)

object ExchangeRates : BreadcrumbItem("Kurzy", Routing.EXCHANGE_RATE)

object Unemployment : BreadcrumbItem("Nezaměstnanost", Routing.EXCHANGE_RATE)

object NationalBudget : BreadcrumbItem("Státní rozpočet", Routing.NATIONAL_BUDGET)

object Salary : BreadcrumbItem("Mzdy", Routing.SALARY)

object About : BreadcrumbItem("O aplikaci", Routing.ABOUT)