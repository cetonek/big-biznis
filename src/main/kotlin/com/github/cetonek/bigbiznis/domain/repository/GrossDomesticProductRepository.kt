package com.github.cetonek.bigbiznis.domain.repository

import com.github.cetonek.bigbiznis.core.db.BigBiznisRepository
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProduct
import com.github.cetonek.bigbiznis.domain.entity.persisted.GrossDomesticProductType
import com.github.cetonek.bigbiznis.domain.entity.GrossDomesticProductByYear
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface GrossDomesticProductRepository : BigBiznisRepository<GrossDomesticProduct, Long> {

    fun getAllByTypeEquals(type: GrossDomesticProductType): List<GrossDomesticProduct>

    @Query("select new com.github.cetonek.bigbiznis.domain.entity.GrossDomesticProductByYear " +
            "(year, sum(gdpMillionsCrowns)) from GrossDomesticProduct where type = ?1 " +
            "group by year having count(year) = 4")
    fun getAllSummedByYearHavingAllFourQuarters(@Param("type") type: GrossDomesticProductType)
            : List<GrossDomesticProductByYear>


}