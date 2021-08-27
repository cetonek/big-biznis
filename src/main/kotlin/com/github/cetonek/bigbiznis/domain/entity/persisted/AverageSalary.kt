package com.github.cetonek.bigbiznis.domain.entity.persisted

import com.github.cetonek.bigbiznis.core.jpa.VersionedPersistableEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "average_salary")
class AverageSalary(
        val quarter: Int,
        val year: Int,
        @Column(name = "salary_crowns") val crowns: Int
) : VersionedPersistableEntity<Long>()

