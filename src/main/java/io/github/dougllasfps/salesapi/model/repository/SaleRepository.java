package io.github.dougllasfps.salesapi.model.repository;

import java.util.List;

import io.github.dougllasfps.salesapi.model.repository.projections.SalePerMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.dougllasfps.salesapi.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true,
            value = "select "
                    + "extract( month from s.date_sale ) as month, "
                    + " sum(s.total) as value"
                    + " from sale as s"
                    + " where extract (year from s.date_sale) = :year"
                    + " group by extract( month from s.date_sale )"
                    + " order by extract( month from s.date_sale )"
    )
    List<SalePerMonth> getSumSalesPerMonth(@Param("year") Integer year);

}
