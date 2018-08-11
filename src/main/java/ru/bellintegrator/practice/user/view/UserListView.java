package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModelProperty;

public class UserListView {
    @ApiModelProperty(hidden = true)
    public Long id;
    public Long office_id;
    public String first_name;
    public String second_name;
    public String middle_name;
    public String position;
}
