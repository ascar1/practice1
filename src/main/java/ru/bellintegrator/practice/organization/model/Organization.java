package ru.bellintegrator.practice.organization.model;

import ru.bellintegrator.practice.organization.view.OrganizationView;

import javax.persistence.*;

/*
        Модель Органиизации
 */
@Entity
@Table(name = "Organization")
public class Organization {
    /*
        Конструктор для Hibernate
     */
    public Organization() {

    }
    public Organization(String Name, String full_name , String inn, String kpp, String address , String phone , Boolean is_active) {
        this.name = Name;
        this.full_name = full_name;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.is_active = is_active;
    }
    public Organization(Long id, String Name, String full_name , String inn, String kpp, String address , String phone , Boolean is_active) {
        this.id = id;
        this.name = Name;
        this.full_name = full_name;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.is_active = is_active;
    }

    @Id
    @GeneratedValue
    @Column(name ="id")
    private Long id;
    @Version
    private Integer version;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "full_name", length = 100, nullable = false)
    private String full_name;
    @Column(name = "inn", length = 10,nullable = false)
    private String inn;
    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;
    @Column(name = "address",length = 100, nullable = false)
    private String address;
    @Column(name = "phone", length = 15,nullable = false)
    private String phone;
    @Column(name = "is_active", nullable = false)
    private Boolean is_active;

    public Long getId() {
        return id;
    }

    public void setName (String val){
        this.name = val;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getInn() {
        return inn;
    }

    public String getKpp() {
        return kpp;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean isIs_active() {
        return is_active;
    }

    public void SetUpdVal (OrganizationView organizationView){
        if (!organizationView.id.equals(id)){
            this.id = organizationView.id;
        }
        if (!organizationView.name.equals(name)){
            this.name = organizationView.name;
        }
        if(!organizationView.full_name.equals(full_name)){
            this.full_name = organizationView.full_name;
        }
        if (!organizationView.inn.equals(inn)){
            this.inn = organizationView.inn;
        }
        if (!organizationView.kpp.equals(kpp)){
            this.kpp = organizationView.kpp;
        }
        if(!organizationView.address.equals(address)){
            this.address = organizationView.address;
        }
        if (!organizationView.phone.equals(phone)){
            this.phone = organizationView.phone;
        }
        if (!organizationView.is_active.equals(is_active)){
            this.is_active = organizationView.is_active;
        }
    }
}
