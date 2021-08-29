package com.github.cetonek.bigbiznis.domain.service

import com.github.cetonek.bigbiznis.application.configuration.BigBiznisConfiguration
import com.github.cetonek.bigbiznis.application.configuration.ExchangeRateConfiguration
import com.github.cetonek.bigbiznis.integration.cnb.CzechNationalBankClient
import com.github.cetonek.bigbiznis.integration.cnb.ExchangeRateDto
import com.github.cetonek.bigbiznis.integration.cnb.ExchangeRateRootDto
import com.github.cetonek.bigbiznis.integration.cnb.ExchangeRateTableDto
import com.github.cetonek.bigbiznis.integration.cnb.toDomain
import com.github.cetonek.bigbiznis.domain.repository.ExchangeRateRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.ResponseEntity
import java.time.LocalDate

@Disabled
@ExtendWith(MockitoExtension::class)
class SynchronizeExchangeRateUseCaseTest {

    @Mock
    lateinit var cnbClient: CzechNationalBankClient

    @Mock
    lateinit var repository: ExchangeRateRepository

    @Mock
    lateinit var configuration: BigBiznisConfiguration

    lateinit var useCase: ExchangeRateService

    @BeforeEach
    fun setUp() {
        useCase = ExchangeRateService(cnbClient, repository, configuration)
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
                .first()

        given(cnbClient.fetchExchangeRateForDay(LocalDate.now())).willReturn(ResponseEntity.ok(returnedDto))
        // when
        useCase.synchronizeTodaysExchangeRates()
        // then
        verify(cnbClient, times(1)).fetchExchangeRateForDay(LocalDate.now())
        verify(repository, times(1)).saveAll(listOf(expectedSaveEntity))
    }


}