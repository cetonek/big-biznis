package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProductEntity
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProductType
import com.github.cetonek.bigbiznis.domain.entity.GrossDomesticProductByYear
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface GrossDomesticProductRepository : JpaRepository<GrossDomesticProductEntity, Long> {

    fun getAllByTypeEquals(type: GrossDomesticProductType): List<GrossDomesticProductEntity>

    @Query("select new com.github.cetonek.bigbiznis.domain.entity.GrossDomesticProductByYear " +
            "(year, sum(gdpMillionsCrowns)) from GrossDomesticProductEntity where type = ?1 " +
            "group by year having count(year) = 4")
    fun getAllSummedByYearHavingAllFourQuarters(@Param("type") type: GrossDomesticProductType)
            : List<GrossDomesticProductByYear>


}