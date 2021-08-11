package com.github.cetonek.bigbiznis.application.web.mvc

import com.github.cetonek.bigbiznis.domain.service.FetchDataSourcesUseCase
import com.github.cetonek.bigbiznis.application.utility.model.About
import com.github.cetonek.bigbiznis.application.utility.model.Home
import com.github.cetonek.bigbiznis.application.utility.model.Routing
import com.github.cetonek.bigbiznis.application.utility.utility.addBreadcrumbs
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(Routing.ABOUT)
class AboutController(private val fetchDataSources: FetchDataSourcesUseCase) {

    @GetMapping
    fun getDataSources(model: Model): String {
        model.addBreadcrumbs(Home, About)

        model["dataSources"] = fetchDataSources.execute()

        return "pages/about"
    }

}