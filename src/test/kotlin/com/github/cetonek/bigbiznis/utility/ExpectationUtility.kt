package com.github.cetonek.bigbiznis.utility

import com.github.cetonek.bigbiznis.core.presentation.model.BreadcrumbItem
import com.github.cetonek.bigbiznis.core.presentation.model.Breadcrumbs
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model

fun breadcrumbs(vararg items: BreadcrumbItem): ResultMatcher {
    return model().attribute("breadcrumbs", Breadcrumbs(items.toList()))
}