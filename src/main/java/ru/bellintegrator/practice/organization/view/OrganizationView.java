package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
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

  @ApiModelProperty(hidden = true)
  public Long id;
  @NotNull
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


