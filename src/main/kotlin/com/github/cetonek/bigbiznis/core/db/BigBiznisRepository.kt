package com.github.cetonek.bigbiznis.core.db

import com.github.cetonek.bigbiznis.domain.entity.persisted.BudgetBalance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BigBiznisRepository<T, ID> : JpaRepository<T, ID> , JpaSpecificationExecutor<T>