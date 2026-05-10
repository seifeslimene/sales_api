package io.github.dougllasfps.salesapi.rest.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dougllasfps.salesapi.model.Product;
import io.github.dougllasfps.salesapi.model.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public List<ProductFormRequest> getList() {
        return repository.findAll().stream()
                .map(ProductFormRequest::fromModel)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductFormRequest> getById(@PathVariable Long id) {
        Optional<Product> existingProduct = repository.findById(id);
        if (existingProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var product = existingProduct.map(ProductFormRequest::fromModel).get();
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ProductFormRequest save(@RequestBody ProductFormRequest product) {
        Product entityProduct = product.toModel();
        repository.save(entityProduct);
        return ProductFormRequest.fromModel(entityProduct);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ProductFormRequest product) {
        Optional<Product> existingProduct = repository.findById(id);

        if (existingProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product entity = product.toModel();
        entity.setId(id);
        repository.save(entity);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Product> existingProduct = repository.findById(id);

        if (existingProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(existingProduct.get());
        return ResponseEntity.noContent().build();
    }

}
