package com.github.cetonek.bigbiznis.application.web.health

import com.github.cetonek.bigbiznis.integration.cnb.CzechNationalBankClient
import com.github.cetonek.bigbiznis.integration.cnb.ExchangeRateRootDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.actuate.health.Status
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.ResourceAccessException

@ExtendWith(MockitoExtension::class)
class CNBHealthIndicatorTest {

    @Mock
    lateinit var client: CzechNationalBankClient

    lateinit var indicator: CNBHealthIndicator

    @BeforeEach
    fun setUp() {
        indicator = CNBHealthIndicator(client)
    }

    @Test
    fun `health up if api returns 200`() {
        // given
        given(client.fetchExchangeRateForDay()).willReturn(ResponseEntity.ok(ExchangeRateRootDto()))
        // when
        val health = indicator.health()
        // then
        assertThat(health.status).isEqualTo(Status.UP)
    }

    @Test
    fun `health down if api returns NON 200`() {
        // given
        given(client.fetchExchangeRateForDay()).willThrow(HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR))
        // when
        val health = indicator.health()
        // then
        assertThat(health.status).isEqualTo(Status.DOWN)
    }

    @Test
    fun `health down if network error occurs`() {
        // given
        given(client.fetchExchangeRateForDay()).willThrow(ResourceAccessException("error"))
        // when
        val health = indicator.health()
        // then
        assertThat(health.status).isEqualTo(Status.DOWN)
    }


}