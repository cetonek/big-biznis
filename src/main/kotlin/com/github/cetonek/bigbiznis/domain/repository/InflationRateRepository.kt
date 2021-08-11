package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationRateEntity
import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationRateKey
import com.github.cetonek.bigbiznis.domain.entity.persisted.InflationType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InflationRateRepository : JpaRepository<InflationRateEntity, InflationRateKey> {

    fun findAllByTypeAndMonthEquals(type: InflationType, month: Int): List<InflationRateEntity>

    fun findAllByTypeEquals(type: InflationType) : List<InflationRateEntity>

    fun findFirstByTypeOrderByYearDescMonthDesc(type: InflationType) : InflationRateEntity

}