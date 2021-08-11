package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.domain.entity.persisted.SalaryEntity
import com.github.cetonek.bigbiznis.domain.entity.persisted.SalaryKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SalaryRepository : JpaRepository<SalaryEntity, SalaryKey>  {

    fun findFirstByOrderByYearDescQuarterDesc() : SalaryEntity

}