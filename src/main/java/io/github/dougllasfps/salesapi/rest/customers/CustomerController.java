package io.github.dougllasfps.salesapi.rest.customers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.dougllasfps.salesapi.model.Customer;
import io.github.dougllasfps.salesapi.model.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @PostMapping
    public ResponseEntity save(@RequestBody CustomerFormRequest request) {
        Customer customer = request.toModel();
        repository.save(customer);
        return ResponseEntity.ok(CustomerFormRequest.fromModel(customer));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @RequestBody CustomerFormRequest request) {

        Optional<Customer> existingCustomer = repository.findById(id);
        if (existingCustomer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Customer customer = request.toModel();
        customer.setId(id);
        repository.save(customer);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerFormRequest> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(CustomerFormRequest::fromModel)
                .map(customerFR -> ResponseEntity.ok(customerFR))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(customer -> {
                    repository.delete(customer);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public Page<CustomerFormRequest> getList(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "ri", required = false, defaultValue = "") String ri,
            Pageable pageable
    ) {
        return repository
                .searchByNameRi("%" + name + "%", "%" + ri + "%", pageable)
                .map(CustomerFormRequest::fromModel);

    }


}
