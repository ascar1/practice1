package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;

public class OrganizationListView {
  @ApiModelProperty(hidden = true)
  public Long id;
  public String name;
  public Boolean is_active;

  @Override
  public String toString() {
    return "{id:" + id + ";name:" + name + "flag" + is_active + "}";
  }
}