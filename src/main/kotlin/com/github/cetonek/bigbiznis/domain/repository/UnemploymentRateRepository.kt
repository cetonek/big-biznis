package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.domain.entity.persisted.UnemploymentRateEntity
import com.github.cetonek.bigbiznis.domain.entity.persisted.UnemploymentRateKey
import com.github.cetonek.bigbiznis.domain.entity.UnemploymentRatePerYearAvg
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UnemploymentRateRepository : JpaRepository<UnemploymentRateEntity, UnemploymentRateKey> {

    @Query("select new com.github.cetonek.bigbiznis.domain.entity.UnemploymentRatePerYearAvg" +
            "(year, avg(unemploymentRatePercent)) from unemployment_rate group by year order by year asc")
    fun getAllYearlyAveragedUnemploymentRates(): List<UnemploymentRatePerYearAvg>

    fun findFirstByOrderByYearDescMonthDesc(): UnemploymentRateEntity

}