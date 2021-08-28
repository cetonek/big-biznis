package com.github.cetonek.bigbiznis.nationalbudget.presentation

import com.github.cetonek.bigbiznis.application.utility.model.Home
import com.github.cetonek.bigbiznis.application.utility.model.NationalBudget
import com.github.cetonek.bigbiznis.application.utility.model.Routing
import com.github.cetonek.bigbiznis.domain.entity.persisted.BudgetBalance
import com.github.cetonek.bigbiznis.domain.entity.persisted.PublicDebt
import com.github.cetonek.bigbiznis.domain.service.FetchNationalBudgetUseCase
import com.github.cetonek.bigbiznis.utility.breadcrumbs
import com.github.cetonek.bigbiznis.application.web.mvc.NationalBudgetController
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(controllers = [NationalBudgetController::class])
internal class NationalBudgetControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var fetchBudget: FetchNationalBudgetUseCase

    val budgetBalance = listOf(BudgetBalance(2015, 1546464))
    val publicDebt = listOf(PublicDebt(2015, 46546))

    @BeforeEach
    fun setUp() {
        given(fetchBudget.fetchBudgetBalance()).willReturn(budgetBalance)
        given(fetchBudget.fetchPublicDebt()).willReturn(publicDebt)
    }

    @Test
    fun `GET hdp returns correct page`() {
        // given
        // when
        // then
        mvc.perform(get(Routing.NATIONAL_BUDGET))
                .andExpect(
                        matchAll(
                                status().isOk,
                                view().name("pages/national_budget")
                        ))

    }

    @Test
    fun `GET hdp returns correct breadcrumbs`() {
        // given
        // when
        // then
        mvc.perform(get(Routing.NATIONAL_BUDGET))
                .andExpect(
                        matchAll(
                                status().isOk,
                                breadcrumbs(Home, NationalBudget)
                        ))

    }

}