package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;

import ru.bellintegrator.practice.organization.model.Organization;

public class OrganizationView {
  public OrganizationView() {

  }

  public OrganizationView(Organization organization) {
    this.id = organization.getId();
    this.name = organization.getName();
    this.full_name = organization.getFull_name();
    this.inn = organization.getFull_name();
    this.kpp = organization.getKpp();
    this.address = organization.getAddress();
    this.phone = organization.getPhone();
    this.is_active = organization.isIs_active();
  }

  public OrganizationView(Long id,
                          String name,
                          String fullName,
                          String inn,
                          String kpp,
                          String address,
                          String phone,
                          boolean isActive) {
    this.id = id;
    this.name = name;
    this.full_name = fullName;
    this.inn = inn;
    this.kpp = kpp;
    this.address = address;
    this.phone = phone;
    this.is_active = isActive;
  }

  @ApiModelProperty(hidden = true)
  public Long id;
  public String name;
  public String full_name;
  public String inn;
  public String kpp;
  public String address;
  public String phone;
  public Boolean is_active;

  @Override
  public String toString() {
    return "{id:" + id + ";name:" + name + ";inn;" + inn + "flag" + is_active + "}";
  }
}


