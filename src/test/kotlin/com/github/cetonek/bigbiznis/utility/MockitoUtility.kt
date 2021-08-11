package com.github.cetonek.bigbiznis.utility

import org.mockito.Mockito

fun <T> any(type: Class<T>): T = Mockito.any<T>(type)