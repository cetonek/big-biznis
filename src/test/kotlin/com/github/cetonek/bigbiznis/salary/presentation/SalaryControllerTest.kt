package com.github.cetonek.bigbiznis.salary.presentation

import com.github.cetonek.bigbiznis.application.utility.model.Home
import com.github.cetonek.bigbiznis.application.utility.model.Routing
import com.github.cetonek.bigbiznis.application.utility.model.Salary
import com.github.cetonek.bigbiznis.domain.entity.persisted.refactored.AverageSalary
import com.github.cetonek.bigbiznis.domain.service.FetchSalaryUseCase
import com.github.cetonek.bigbiznis.utility.breadcrumbs
import com.github.cetonek.bigbiznis.application.web.mvc.SalaryController
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(controllers = [SalaryController::class])
class SalaryControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var fetchSalary: FetchSalaryUseCase

    @BeforeEach
    fun setUp() {
        given(fetchSalary.fetchAll())
                .willReturn(listOf(
                        AverageSalary(quarter = 4,
                                year = 2018,
                                crowns = 35785)
                ))
    }

    @Test
    fun `GET mzdy returns correct page`() {
        // given
        // when
        // then
        mvc.perform(MockMvcRequestBuilders.get(Routing.SALARY))
                .andExpect(
                        matchAll(
                                status().isOk,
                                view().name("pages/salary")
                        )
                )
    }

    @Test
    fun `GET mzdy returns correct breadcrumbs`() {
        // given
        // when
        // then
        mvc.perform(MockMvcRequestBuilders.get(Routing.SALARY))
                .andExpect(
                        matchAll(breadcrumbs(Home, Salary))
                )
    }

}