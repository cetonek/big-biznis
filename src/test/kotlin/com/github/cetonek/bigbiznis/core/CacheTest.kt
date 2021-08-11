package com.github.cetonek.bigbiznis.core

import com.github.cetonek.bigbiznis.TestProfile
import com.github.cetonek.bigbiznis.core.domain.EvictAllCacheUseCase
import com.github.cetonek.bigbiznis.salary.data.database.SalaryEntity
import com.github.cetonek.bigbiznis.salary.data.database.SalaryRepository
import com.github.cetonek.bigbiznis.salary.domain.FetchSalaryUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
@TestProfile
class CacheTest {

    @Autowired
    lateinit var fetchSalary: FetchSalaryUseCase

    @Autowired
    lateinit var evictCache: EvictAllCacheUseCase

    @MockBean
    lateinit var salaryRepository: SalaryRepository

    @BeforeEach
    fun setUp() {
        evictCache.execute()
    }

    @Test
    fun `fetch all is cached if result isnt empty`() {
        // given
        given(salaryRepository.findAll()).willReturn(
                listOf(SalaryEntity()))
        // when
        fetchSalary.fetchAll()
        fetchSalary.fetchAll()
        fetchSalary.fetchAll()
        // then
        verify(salaryRepository, times(1)).findAll()
        verifyNoMoreInteractions(salaryRepository)
    }


    @Test
    fun `fetch all is NOT cached when result is empty`() {
        // given
        given(salaryRepository.findAll()).willReturn(
                listOf())
        // when
        fetchSalary.fetchAll()
        fetchSalary.fetchAll()
        fetchSalary.fetchAll()
        // then
        verify(salaryRepository, times(3)).findAll()
    }

    @Test
    fun `fetch all is cached then evicted and then cached again`() {
        // given
        given(salaryRepository.findAll()).willReturn(
                listOf(SalaryEntity()))
        // when
        fetchSalary.fetchAll()

        evictCache.execute()

        fetchSalary.fetchAll()
        fetchSalary.fetchAll()
        // then
        verify(salaryRepository, times(2)).findAll()
    }


}