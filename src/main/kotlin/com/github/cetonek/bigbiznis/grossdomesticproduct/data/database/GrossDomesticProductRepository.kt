package com.github.cetonek.bigbiznis.grossdomesticproduct.data.database

import com.github.cetonek.bigbiznis.grossdomesticproduct.domain.model.GrossDomesticProductByYear
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface GrossDomesticProductRepository : JpaRepository<GrossDomesticProductEntity, GrossDomesticProductKey> {

    fun getAllByTypeEquals(type: GrossDomesticProductType) : List<GrossDomesticProductEntity>

    @Query("select new com.github.cetonek.bigbiznis.grossdomesticproduct.domain.model.GrossDomesticProductByYear " +
            "(year, sum(gdpMillionsCrowns)) from gross_domestic_product where type = ?1 " +
            "group by year having count(year) = 4")
    fun getAllSummedByYearHavingAllFourQuarters(@Param("type") type: GrossDomesticProductType)
            : List<GrossDomesticProductByYear>


}