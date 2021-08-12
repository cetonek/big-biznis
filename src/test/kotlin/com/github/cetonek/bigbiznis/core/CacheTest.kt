package com.github.cetonek.bigbiznis.core

import com.github.cetonek.bigbiznis.TestProfile
import com.github.cetonek.bigbiznis.domain.service.EvictAllCacheUseCase
import com.github.cetonek.bigbiznis.domain.entity.persisted.refactored.AverageSalary
import com.github.cetonek.bigbiznis.domain.repository.AverageSalaryRepository
import com.github.cetonek.bigbiznis.domain.service.FetchSalaryUseCase
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
    lateinit var averageSalaryRepository: AverageSalaryRepository

    @BeforeEach
    fun setUp() {
        evictCache.execute()
    }

    @Test
    fun `fetch all is cached if result isnt empty`() {
        // given
        given(averageSalaryRepository.findAll()).willReturn(
                listOf(
                        AverageSalary(quarter = 4, year = 2020, crowns = 25000)
                ))
        // when
        fetchSalary.fetchAll()
        fetchSalary.fetchAll()
        fetchSalary.fetchAll()
        // then
        verify(averageSalaryRepository, times(1)).findAll()
        verifyNoMoreInteractions(averageSalaryRepository)
    }


    @Test
    fun `fetch all is NOT cached when result is empty`() {
        // given
        given(averageSalaryRepository.findAll()).willReturn(
                listOf())
        // when
        fetchSalary.fetchAll()
        fetchSalary.fetchAll()
        fetchSalary.fetchAll()
        // then
        verify(averageSalaryRepository, times(3)).findAll()
    }

    @Test
    fun `fetch all is cached then evicted and then cached again`() {
        // given
        given(averageSalaryRepository.findAll()).willReturn(
                listOf(AverageSalary(quarter = 4, year = 2020, crowns = 25000)))
        // when
        fetchSalary.fetchAll()

        evictCache.execute()

        fetchSalary.fetchAll()
        fetchSalary.fetchAll()
        // then
        verify(averageSalaryRepository, times(2)).findAll()
    }


}