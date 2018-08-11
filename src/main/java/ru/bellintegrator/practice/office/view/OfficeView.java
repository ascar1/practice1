package ru.bellintegrator.practice.office.view;

import io.swagger.annotations.ApiModelProperty;

public class OfficeView {
    @ApiModelProperty(hidden = true)
    public Long id;
    public Long org_id;
    public String name;
    public String address;
    public String phone;
    public Boolean is_active;

    @Override
    public String toString (){
        return "{id:" + id + ";name:" + name + "flag" + is_active +"}";
    }

}
