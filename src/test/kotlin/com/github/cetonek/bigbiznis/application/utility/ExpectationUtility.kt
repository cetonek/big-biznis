package com.github.cetonek.bigbiznis.application.utility

import com.github.cetonek.bigbiznis.application.utility.model.BreadcrumbItem
import com.github.cetonek.bigbiznis.application.utility.model.Breadcrumbs
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model

fun breadcrumbs(vararg items: BreadcrumbItem): ResultMatcher {
    return model().attribute("breadcrumbs", Breadcrumbs(items.toList()))
}