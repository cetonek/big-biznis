package com.github.cetonek.bigbiznis.domain.service

import com.github.cetonek.bigbiznis.integration.cnb.CzechNationalBankClient
import com.github.cetonek.bigbiznis.integration.cnb.toEntity
import com.github.cetonek.bigbiznis.domain.repository.GrossDomesticProductRepository
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProductType
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SynchronizeRealGrossDomesticProduct(private val api: CzechNationalBankClient,
                                          private val repository: GrossDomesticProductRepository
) {

    val startingDate = LocalDate.of(1996, 1, 1)
    val gdpType = GrossDomesticProductType.REAL_2010_PRICES

    fun execute() {
        api.fetchQuarterlyRealGdp2010Prices(startingDate)
                .map { it.toEntity(gdpType) }
                .toList()
                .also { repository.saveAll(it) }
    }

}