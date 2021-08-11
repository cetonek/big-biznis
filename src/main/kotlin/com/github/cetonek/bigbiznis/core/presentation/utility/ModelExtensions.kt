package com.github.cetonek.bigbiznis.core.presentation.utility

import com.github.cetonek.bigbiznis.core.presentation.model.BreadcrumbItem
import com.github.cetonek.bigbiznis.core.presentation.model.Breadcrumbs
import org.springframework.ui.Model

fun Model.addBreadcrumbs(vararg items: BreadcrumbItem) {
    this.addAttribute("breadcrumbs", Breadcrumbs(items.toList()))
}