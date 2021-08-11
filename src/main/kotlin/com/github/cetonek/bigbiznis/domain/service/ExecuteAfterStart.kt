package com.github.cetonek.bigbiznis.domain.service

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener

@EventListener(ApplicationReadyEvent::class)
annotation class ExecuteAfterStart