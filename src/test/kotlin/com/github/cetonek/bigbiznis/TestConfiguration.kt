package com.github.cetonek.bigbiznis

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfiguration {

    @Bean
    fun fileReader() = FileReader()

}