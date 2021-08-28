package com.github.cetonek.bigbiznis

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@TestProfile
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
annotation class DatabaseTest

@ActiveProfiles("dev")
annotation class DevProfile

@ActiveProfiles("junit")
annotation class TestProfile