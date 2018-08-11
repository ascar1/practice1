package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class OrganizationView {

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
        return "{id:" + id + ";name:" + name + ";inn;" + inn + "flag" + is_active +"}";
    }
}


