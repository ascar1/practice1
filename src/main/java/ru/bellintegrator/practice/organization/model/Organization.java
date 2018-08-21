package ru.bellintegrator.practice.organization.model;

import javax.persistence.*;
import java.util.List;

import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.view.OrganizationView;

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

  public Organization(String name,
                      String fullName,
                      String inn,
                      String kpp,
                      String address,
                      String phone,
                      Boolean isActive) {
    this.name = name;
    this.full_name = fullName;
    this.inn = inn;
    this.kpp = kpp;
    this.address = address;
    this.phone = phone;
    this.is_active = isActive;
  }

  public Organization(Long id,
                      String name,
                      String fullName,
                      String inn,
                      String kpp,
                      String address,
                      String phone,
                      Boolean isActive) {
    this.id = id;
    this.name = name;
    this.full_name = fullName;
    this.inn = inn;
    this.kpp = kpp;
    this.address = address;
    this.phone = phone;
    this.is_active = isActive;
  }

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;
  @Version
  private Integer version;
  @Column(name = "name", length = 50, nullable = false)
  private String name;
  @Column(name = "full_name", length = 100, nullable = false)
  private String full_name;
  @Column(name = "inn", length = 10, nullable = false)
  private String inn;
  @Column(name = "kpp", length = 9, nullable = false)
  private String kpp;
  @Column(name = "address", length = 100, nullable = false)
  private String address;
  @Column(name = "phone", length = 15, nullable = false)
  private String phone;
  @Column(name = "is_active", nullable = false)
  private Boolean is_active;

  @OneToMany(mappedBy = "org")
  private List<Office> offices;

  public void addOffices(Office office) {
    this.offices.add(office);
    office.setOrg(this);
  }

  public Long getId() {
    return id;
  }

  public void setName(String val) {
    this.name = val;
  }

  public String getName() {
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

  public void setUpdVal(OrganizationView organizationView) {
    this.id = organizationView.id;
    this.name = organizationView.name;
    this.full_name = organizationView.full_name;
    this.inn = organizationView.inn;
    this.kpp = organizationView.kpp;
    this.address = organizationView.address;
    this.phone = organizationView.phone;
    this.is_active = organizationView.is_active;
  }
}
