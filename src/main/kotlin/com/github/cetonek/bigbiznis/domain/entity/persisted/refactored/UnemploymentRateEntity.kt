package com.github.cetonek.bigbiznis.domain.entity.persisted.refactored

import com.github.cetonek.bigbiznis.core.jpa.VersionedPersistableEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "unemployment_rate")
class UnemploymentRateEntity(

        var year: Int,

        var month: Int,

        var unemploymentPercent: Float

) : VersionedPersistableEntity<Long>()

