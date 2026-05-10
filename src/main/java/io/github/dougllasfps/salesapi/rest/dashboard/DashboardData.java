package io.github.dougllasfps.salesapi.rest.dashboard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.github.dougllasfps.salesapi.model.repository.projections.SalePerMonth;

public class DashboardData {

    private Long products;
    private Long customers;
    private Long sales;
    private List<SalePerMonth> salesPerMonth;

    public DashboardData(Long products, Long customers, Long sales, List<SalePerMonth> salesPerMonth) {
        super();
        this.products = products;
        this.customers = customers;
        this.sales = sales;
        this.salesPerMonth = salesPerMonth;
        this.fillInMissingMonths();
    }

    public Long getProducts() {
        return products;
    }

    public void setProducts(Long products) {
        this.products = products;
    }

    public Long getCustomers() {
        return customers;
    }

    public void setCustomers(Long customers) {
        this.customers = customers;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public List<SalePerMonth> getSalesPerMonth() {
        if (salesPerMonth == null) {
            salesPerMonth = new ArrayList<>();
        }
        return salesPerMonth;
    }

    public void setSalesPerMonth(List<SalePerMonth> salesPerMonth) {
        this.salesPerMonth = salesPerMonth;
    }

    public void fillInMissingMonths() {
        if (getSalesPerMonth().isEmpty()) {
            return;
        }


        Integer monthMaximum = getSalesPerMonth()
                .stream()
                .mapToInt(SalePerMonth::getMonth)
                .max().getAsInt();
        List<Integer> listMonths = IntStream
                .rangeClosed(1, monthMaximum)
                .boxed().collect(Collectors.toList());

        List<Integer> monthsAdded = getSalesPerMonth()
                .stream()
                .map(SalePerMonth::getMonth)
                .collect(Collectors.toList());

        listMonths.stream().forEach(month -> {
            if (!monthsAdded.contains(month)) {
                SalePerMonth salePerMonth = new SalePerMonth() {

                    @Override
                    public BigDecimal getValue() {
                        return BigDecimal.ZERO;
                    }

                    @Override
                    public Integer getMonth() {
                        return month;
                    }
                };

                getSalesPerMonth().add(salePerMonth);
            }
        });

        getSalesPerMonth().sort(Comparator.comparing(SalePerMonth::getMonth));
    }
}
