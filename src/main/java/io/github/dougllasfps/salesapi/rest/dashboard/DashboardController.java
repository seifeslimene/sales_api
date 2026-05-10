package io.github.dougllasfps.salesapi.rest.dashboard;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dougllasfps.salesapi.model.repository.CustomerRepository;
import io.github.dougllasfps.salesapi.model.repository.ProductRepository;
import io.github.dougllasfps.salesapi.model.repository.SaleRepository;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private SaleRepository sales;
    @Autowired
    private CustomerRepository customers;
    @Autowired
    private ProductRepository products;

    @GetMapping
    public DashboardData getDashBoard() {
        long salesCount = sales.count();
        long customersCount = customers.count();
        long productsCount = products.count();

        var yearCurrent = LocalDate.now().getYear();
        var salesPerMonth = sales.getSumSalesPerMonth(yearCurrent);

        return new DashboardData(productsCount, customersCount, salesCount, salesPerMonth);

    }
}
