package com.github.cetonek.bigbiznis.exchangerate.domain

import com.github.cetonek.bigbiznis.exchangerate.data.database.ExchangeRateRepository
import com.github.cetonek.bigbiznis.exchangerate.data.database.toEntity
import com.github.cetonek.bigbiznis.utility.exampleRate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ExchangeRateServiceTest {

    @Mock
    lateinit var repository: ExchangeRateRepository
    @Mock
    lateinit var synchronizeUseCase: SynchronizeExchangeRateUseCase

    lateinit var useCaseFetch: FetchExchangeRateUseCase

    @BeforeEach
    fun setUp() {
        useCaseFetch = FetchExchangeRateUseCase(repository, synchronizeUseCase)
    }

    @Test
    fun `service fetches latest exchange rates`() {
        // given
        given(repository.findAllRatesFromLastDay()).willReturn(listOf(exampleRate.toEntity()))
        // when
        val result = useCaseFetch.fetchLatestRates()
        // then
        assertThat(result.size).isEqualTo(1)
        assertThat(result.first()).isEqualTo(exampleRate)
    }

}