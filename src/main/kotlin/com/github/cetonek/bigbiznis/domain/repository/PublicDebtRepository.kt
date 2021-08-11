package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.domain.entity.persisted.PublicDebtEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PublicDebtRepository : JpaRepository<PublicDebtEntity, Int> {

    fun findFirstByOrderByYearDesc(): PublicDebtEntity

}