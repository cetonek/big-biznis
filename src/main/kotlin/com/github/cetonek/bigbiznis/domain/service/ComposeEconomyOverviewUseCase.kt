package com.github.cetonek.bigbiznis.domain.service

import com.github.cetonek.bigbiznis.application.configuration.DashboardConfiguration
import com.github.cetonek.bigbiznis.application.utility.formatting.*
import com.github.cetonek.bigbiznis.domain.entity.persisted.ExchangeRate
import com.github.cetonek.bigbiznis.domain.repository.ExchangeRateRepository
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProductType.REAL_2010_PRICES
import com.github.cetonek.bigbiznis.domain.repository.InflationRateRepository
import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationType
import com.github.cetonek.bigbiznis.domain.repository.UnemploymentRateRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ComposeEconomyOverviewUseCase(private val exchangeRepository: ExchangeRateRepository,
                                    private val inflationRepository: InflationRateRepository,
                                    private val unemploymentRepository: UnemploymentRateRepository,
                                    private val fetchGdp: FetchGrossDomesticProductUseCase,
                                    private val configuration: DashboardConfiguration,
                                    private val fetchNationalBudget: FetchNationalBudgetUseCase,
                                    private val fetchSalaryUseCase: FetchSalaryUseCase
) {

    @Cacheable("ComposeEconomyOverviewUseCase::execute")
    fun execute(): EconomyOverview {

        val rates = exchangeRepository.findAllRatesFromLastDayWhereCodeLike(configuration.exchangeRates)
        val ratesDate = rates.first().date

        return EconomyOverview(
                exchangeRate = ExchangeRatesOverview(date = ratesDate, rates = rates),
                firstOverviewItems = listOf(
                        getInflation(),
                        getGdp(),
                        getUnemp()),
                secondOverviewItems = listOf(
                        getSalary(),
                        getPublicDebt(),
                        getBudgetBalance())
        )
    }

    private fun getBudgetBalance(): Triple<*, *, *> {
        val budgetBalance = fetchNationalBudget.fetchCurrentBudgetBalance()
        return Triple(
                "Saldo rozpo??tu",
                budgetBalance.year,
                budgetBalance.millionsCrowns.millionsCzechCrowns
        )
    }

    private fun getPublicDebt(): Triple<*, *, *> {
        val publicDebt = fetchNationalBudget.findCurrentPublicDebt()
        return Triple(
                "St??tn?? dluh",
                publicDebt.year,
                publicDebt.millionsCrowns.millionsCzechCrowns
        )
    }

    private fun getGdp(): Triple<*, *, *> {
        val latestGdp = fetchGdp.fetchPercentChangesPerQuarter(REAL_2010_PRICES).last()
        return Triple(
                "HDP",
                QuarterAndYear(latestGdp.dataPoint.quarter, latestGdp.dataPoint.year),
                latestGdp.value.percentage)
    }

    private fun getSalary(): Triple<*, *, *> {
        val averageSalary = fetchSalaryUseCase.fetchLatest()
        return Triple(
                "Pr??m??rn?? mzda",
                QuarterAndYear(averageSalary.quarter, averageSalary.year),
                averageSalary.crowns.czechCrowns
        )
    }

    private fun getUnemp(): Triple<*, *, *> {
        val unemployment = unemploymentRepository.findFirstByOrderByYearDescMonthDesc()
        return Triple(
                "Nezam??stnanost",
                MonthAndYear(unemployment.month, unemployment.year),
                unemployment.unemploymentPercent.percentage
        )
    }

    private fun getInflation(): Triple<*, *, *> {
        val inflation = inflationRepository.findFirstByTypeOrderByYearDescMonthDesc(InflationType.THIS_YEAR_VS_LAST_YEAR)
        return Triple(
                "Inflace",
                MonthAndYear(inflation.month, inflation.year),
                inflation.inflationPercent.percentage
        )
    }

}

data class EconomyOverview(val exchangeRate: ExchangeRatesOverview,
                           val firstOverviewItems: List<Triple<*, *, *>>,
                           val secondOverviewItems: List<Triple<*, *, *>>
)

data class ExchangeRatesOverview(val date: LocalDate,
                                 val rates: Collection<ExchangeRate>)
