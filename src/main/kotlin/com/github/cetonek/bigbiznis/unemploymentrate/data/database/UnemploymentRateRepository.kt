package com.github.cetonek.bigbiznis.unemploymentrate.data.database

import com.github.cetonek.bigbiznis.unemploymentrate.domain.model.UnemploymentRatePerYearAvg
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UnemploymentRateRepository : JpaRepository<UnemploymentRateEntity, UnemploymentRateKey> {

    @Query("select new com.github.cetonek.bigbiznis.unemploymentrate.domain.model.UnemploymentRatePerYearAvg" +
            "(year, avg(unemploymentRatePercent)) from unemployment_rate group by year order by year asc")
    fun getAllYearlyAveragedUnemploymentRates() : List<UnemploymentRatePerYearAvg>

    fun findFirstByOrderByYearDescMonthDesc() : UnemploymentRateEntity

}