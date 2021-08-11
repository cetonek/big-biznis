package com.github.cetonek.bigbiznis.application.utility.model

object Routing {

    const val DASHBOARD = "/"

    const val INFLATION = "/inflace"
    const val EXCHANGE_RATE = "/kurzy"
    const val GDP = "/hdp"
    const val UNEMPLOYMENT = "/nezamestnanost"
    const val NATIONAL_BUDGET = "/statni-rozpocet"
    const val SALARY = "/mzdy"
    const val ABOUT = "/o-aplikaci"


    fun collectAll() =
            listOf(DASHBOARD, INFLATION, EXCHANGE_RATE, GDP, UNEMPLOYMENT, NATIONAL_BUDGET, SALARY, ABOUT)


}