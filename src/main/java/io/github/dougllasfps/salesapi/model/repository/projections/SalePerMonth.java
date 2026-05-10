package io.github.dougllasfps.salesapi.model.repository.projections;

import java.math.BigDecimal;

public interface SalePerMonth {
    Integer getMonth();

    BigDecimal getValue();
}
