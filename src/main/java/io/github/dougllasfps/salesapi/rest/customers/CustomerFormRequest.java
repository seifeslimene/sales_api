package io.github.dougllasfps.salesapi.rest.customers;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.dougllasfps.salesapi.model.Customer;

public class CustomerFormRequest {

    private Long id;
    private String name;
    private String ri;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private String address;
    private String email;
    private String telephone;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate register;

    public CustomerFormRequest() {
        super();
    }

    /**
     * @param id
     * @param name
     * @param ri
     * @param birthDate
     * @param address
     * @param email
     * @param telephone
     * @param register
     */
    public CustomerFormRequest(Long id, String name, String ri, LocalDate birthDate, String address, String email,
            String telephone, LocalDate register) {
        super();
        this.id = id;
        this.name = name;
        this.ri = ri;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.register = register;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRi() {
        return ri;
    }

    public void setRi(String ri) {
        this.ri = ri;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getRegister() {
        return register;
    }

    public void setRegister(LocalDate register) {
        this.register = register;
    }

    public Customer toModel() {
        return new Customer(id, birthDate, ri, name, address, telephone, email, register);
    }

    public static CustomerFormRequest fromModel(Customer customer) {
        return new CustomerFormRequest(customer.getId(), customer.getName(),
                customer.getRu(), customer.getBirth(),
                customer.getAdress(), customer.getEmail(),
                customer.getPhone(), customer.getDateRegister());
    }

}
