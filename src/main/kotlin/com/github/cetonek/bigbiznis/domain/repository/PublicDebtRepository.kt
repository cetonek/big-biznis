package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.domain.entity.persisted.refactored.PublicDebt
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface PublicDebtRepository : JpaRepository<PublicDebt, Long> {

    @Transactional(readOnly = true)
    fun findFirstByOrderByYearDesc(): PublicDebt

}