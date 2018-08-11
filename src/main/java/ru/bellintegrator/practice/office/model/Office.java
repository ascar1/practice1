package ru.bellintegrator.practice.office.model;

import javax.persistence.*;

@Entity
@Table(name = "office")
public class Office {
    public Office(){

    }
    public Office (Long id, Long org_id, String name, String address, String phone, Boolean is_active){
        this.id = id;
        this.org_id = org_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.is_active = is_active;
    }
    public Office (Long org_id, String name, String address, String phone, Boolean is_active){
        this.org_id = org_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.is_active = is_active;
    }

    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Version
    private Integer version;
    @Column(name = "org_id")
    private Long org_id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "address", length = 100, nullable = false)
    private String address;
    @Column(name = "phone", length = 15, nullable = false)
    private String phone;
    @Column(name= "is_active", nullable = false)
    private Boolean is_active;


    public Long getId() {
        return id;
    }
    public Long getOrg_id(){return org_id;}
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public Boolean isIs_active() {
        return is_active;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
