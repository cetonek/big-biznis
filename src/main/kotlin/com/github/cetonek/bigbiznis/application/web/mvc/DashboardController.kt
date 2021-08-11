package com.github.cetonek.bigbiznis.application.web.mvc

import com.github.cetonek.bigbiznis.application.utility.model.Routing
import com.github.cetonek.bigbiznis.domain.service.ComposeDashboardUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller(Routing.DASHBOARD)
class DashboardController(private val composeDashboard: ComposeDashboardUseCase) {

    @GetMapping
    fun getDashboard(model: Model): String {
        model.addAttribute("dashboard", composeDashboard.execute())

        return "pages/dashboard"
    }


}
