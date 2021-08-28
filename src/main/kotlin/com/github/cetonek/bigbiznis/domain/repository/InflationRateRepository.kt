package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationRate
import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InflationRateRepository : JpaRepository<InflationRate, Long> {

    fun findAllByTypeAndMonthEquals(type: InflationType, month: Int): List<InflationRate>

    fun findAllByTypeEquals(type: InflationType): List<InflationRate>

    fun findFirstByTypeOrderByYearDescMonthDesc(type: InflationType): InflationRate

}