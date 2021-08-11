package com.github.cetonek.bigbiznis.salary.data.database

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SalaryRepository : JpaRepository<SalaryEntity, SalaryKey>  {

    fun findFirstByOrderByYearDescQuarterDesc() : SalaryEntity

}