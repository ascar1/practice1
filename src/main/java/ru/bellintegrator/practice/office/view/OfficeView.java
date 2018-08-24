package ru.bellintegrator.practice.office.view;

import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.practice.office.model.Office;

public class OfficeView {
  public OfficeView(){}

  public OfficeView(Office office) {
    this.id = office.getId();
    this.org_id = office.getOrg_id();
    this.name = office.getName();
    this.address = office.getAddress();
    this.phone = office.getPhone();
    this.is_active = office.isIs_active();
  }

  public OfficeView(Long id,Long orgId,String name,String address,String phone,Boolean isActive) {
    this.id = id;
    this.org_id = orgId;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.is_active = isActive;
  }
  @ApiModelProperty(hidden = true)
  public Long id;
  public Long org_id;
  public String name;
  public String address;
  public String phone;
  public Boolean is_active;

  @Override
  public String toString() {
    return "{id:" + id + ";name:" + name + "flag" + is_active + "}";
  }
}
