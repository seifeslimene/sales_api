package io.github.dougllasfps.salesapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dougllasfps.salesapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
