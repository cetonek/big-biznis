package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.core.db.BigBiznisRepository
import com.github.cetonek.bigbiznis.domain.entity.persisted.PublicDebt
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PublicDebtRepository : BigBiznisRepository<PublicDebt, Long> {

    @Transactional(readOnly = true)
    fun findFirstByOrderByYearDesc(): PublicDebt

}