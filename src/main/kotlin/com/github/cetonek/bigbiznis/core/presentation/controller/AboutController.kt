package com.github.cetonek.bigbiznis.core.presentation.controller

import com.github.cetonek.bigbiznis.core.domain.FetchDataSourcesUseCase
import com.github.cetonek.bigbiznis.core.presentation.model.About
import com.github.cetonek.bigbiznis.core.presentation.model.Home
import com.github.cetonek.bigbiznis.core.presentation.model.Routing
import com.github.cetonek.bigbiznis.core.presentation.utility.addBreadcrumbs
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