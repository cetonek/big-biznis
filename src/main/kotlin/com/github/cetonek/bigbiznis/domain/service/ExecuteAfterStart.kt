package com.github.cetonek.bigbiznis.domain.service

import org.springframework.scheduling.annotation.Scheduled

@Scheduled(fixedDelay = Long.MAX_VALUE)
annotation class ExecuteAfterStart