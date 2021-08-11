package com.github.cetonek.bigbiznis.application.utility.utility

import com.github.cetonek.bigbiznis.application.utility.model.BreadcrumbItem
import com.github.cetonek.bigbiznis.application.utility.model.Breadcrumbs
import org.springframework.ui.Model

fun Model.addBreadcrumbs(vararg items: BreadcrumbItem) {
    this.addAttribute("breadcrumbs", Breadcrumbs(items.toList()))
}