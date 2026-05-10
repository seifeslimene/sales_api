package io.github.dougllasfps.salesapi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate birth;
    private String ru;
    private String name;
    private String adress;
    private String phone;
    private String email;

    @Column(name = "date_register")
    private LocalDate dateRegister;

    public Customer() {
        super();
    }

    /**
     * @param birth
     * @param ru
     * @param name
     * @param adress
     * @param phone
     * @param email
     */
    public Customer(Long id, LocalDate birth, String ru, String name, String adress, String phone, String email, LocalDate dateRegister) {
        super();
        this.id = id;
        this.birth = birth;
        this.ru = ru;
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        this.dateRegister = dateRegister;
    }


    @PrePersist
    public void prePersist() {
        setDateRegister(LocalDate.now());
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", birth=" + birth +
                ", ru='" + ru + '\'' +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dateRegister=" + dateRegister +
                '}';
    }
}
