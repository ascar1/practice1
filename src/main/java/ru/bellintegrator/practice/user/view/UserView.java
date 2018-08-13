package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserView {
    @ApiModelProperty(hidden = true)
    public Long id;
    public Long office_id;
    public String first_name;
    public String second_name;
    public String middle_name;
    public String position;
    public String phone;
    public Integer doc_Code;
    public String doc_name;
    public String doc_number;
    public Date doc_date;
    public Integer citizenship_code;
    public Boolean is_identified;
}
