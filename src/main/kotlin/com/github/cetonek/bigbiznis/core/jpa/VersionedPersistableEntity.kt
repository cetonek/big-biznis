package com.github.cetonek.bigbiznis.core.jpa

import org.hibernate.sql.Update
import java.io.Serializable
import javax.persistence.MappedSuperclass
import javax.persistence.Version
import javax.validation.constraints.NotNull

@MappedSuperclass
abstract class VersionedPersistableEntity<PK : Serializable?>
    : IdentityGeneratedIdAbstractPersistable<PK?>() {

    @Version
    @NotNull(groups = [Update::class])
    var version: Long? = null

}