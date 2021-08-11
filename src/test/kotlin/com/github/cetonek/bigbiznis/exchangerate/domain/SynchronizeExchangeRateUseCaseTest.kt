package com.github.cetonek.bigbiznis.exchangerate.domain

import com.github.cetonek.bigbiznis.application.configuration.ExchangeRateConfiguration
import com.github.cetonek.bigbiznis.integration.CNBClient
import com.github.cetonek.bigbiznis.integration.ExchangeRateDto
import com.github.cetonek.bigbiznis.integration.ExchangeRateRootDto
import com.github.cetonek.bigbiznis.integration.ExchangeRateTableDto
import com.github.cetonek.bigbiznis.integration.toDomain
import com.github.cetonek.bigbiznis.domain.repository.ExchangeRateRepository
import com.github.cetonek.bigbiznis.domain.entity.persisted.toEntity
import com.github.cetonek.bigbiznis.domain.service.SynchronizeExchangeRateUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.ResponseEntity
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class SynchronizeExchangeRateUseCaseTest {

    @Mock
    lateinit var cnbClient: CNBClient
    @Mock
    lateinit var repository: ExchangeRateRepository
    @Mock
    lateinit var configuration: ExchangeRateConfiguration

    lateinit var useCase: SynchronizeExchangeRateUseCase

    @BeforeEach
    fun setUp() {
        useCase = SynchronizeExchangeRateUseCase(cnbClient, repository, configuration)
    }

    @Test
    fun `use case synchronizes current rates`() {
        // given
        val returnedDto = ExchangeRateRootDto()

        with(returnedDto) {
            bankName = "CNB"
            date = LocalDate.now()
            order = 10
            val exchangeRateTableDto = ExchangeRateTableDto()
            with(exchangeRateTableDto) {
                type = "kurz"
                val rate = ExchangeRateDto()
                with(rate) {
                    currencyCode = "USD"
                    currencyName = "dolar"
                    amount = 1
                    this.rate = 22.5
                    country = "USA"

                }
                rates = listOf(rate)
            }
            exchangeRatesTableDto = exchangeRateTableDto
        }
        val expectedSaveEntity = returnedDto.toDomain()
                .map { it.toEntity() }
                .first()

        given(cnbClient.fetchExchangeRateForDay(LocalDate.now())).willReturn(ResponseEntity.ok(returnedDto))
        // when
        useCase.executeForToday()
        // then
        verify(cnbClient, times(1)).fetchExchangeRateForDay(LocalDate.now())
        verify(repository, times(1)).saveAll(listOf(expectedSaveEntity))
    }


}