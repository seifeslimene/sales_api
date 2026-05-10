package io.github.dougllasfps.salesapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.dougllasfps.salesapi.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(" select c from Customer c where upper(c.name) like upper(:name) "
            + " and c.ru like :ru  ")
    Page<Customer> searchByNameRi(
            @Param("name") String name,
            @Param("ru") String ru,
            Pageable pageable);

}
