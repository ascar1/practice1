package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.practice.user.model.User;

import java.util.Date;

public class UserView {
  public UserView(){

  }

  public UserView(User user) {
    this.id = user.getId();
    this.office_id = user.getOfficeID();
    this.first_name = user.getFirst_name();
    this.second_name = user.getSecond_name();
    this.middle_name = user.getMiddle_name();
    this.position = user.getPosition();
    this.phone = user.getPhone();
    //this.doc_Code = user.getDocs();
    this.doc_name = user.getDoc_name();
    this.doc_number = user.getDoc_number();
    this.doc_date = user.getDoc_date();
    this.citizenship_code = user.getCountries();
    this.is_identified = user.isIs_identified();
  }

  public UserView(Long id,
                  Long officeId,
                  String firstName,
                  String secondName,
                  String middleName,
                  String position,
                  String phone,
                  Long docCode,
                  String docName,
                  String docNumber,
                  Date docDate,
                  Integer citizenshipCode,
                  Boolean isIdentified) {
    this.id = id;
    this.office_id = officeId;
    this.first_name = firstName;
    this.second_name = secondName;
    this.middle_name = middleName;
    this.position = position;
    this.phone = phone;
    this.doc_Code = docCode;
    this.doc_name = docName;
    this.doc_number = docNumber;
    this.doc_date = docDate;
    this.citizenship_code = citizenshipCode;
    this.is_identified = isIdentified;

  }

  @ApiModelProperty(hidden = true)
  public Long id;
  public Long office_id;
  public String first_name;
  public String second_name;
  public String middle_name;
  public String position;
  public String phone;
  public Long doc_Code;
  public String doc_name;
  public String doc_number;
  public Date doc_date;
  public Integer citizenship_code;
  public Boolean is_identified;
}
