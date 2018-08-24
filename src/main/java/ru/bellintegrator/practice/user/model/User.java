package ru.bellintegrator.practice.user.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.practice.countries.model.Countries;
import ru.bellintegrator.practice.docs.model.Docs;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.user.view.UserView;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {
  private static Logger log = LoggerFactory.getLogger(OrganizationDao.class.getName());
  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;
  @Version
  private Integer Version;
  @Column(name = "first_name", length = 50, nullable = false)
  private String first_name;
  @Column(name = "second_name", length = 50, nullable = false)
  private String second_name;
  @Column(name = "middle_name", length = 50, nullable = false)
  private String middle_name;
  @Column(name = "position", length = 30, nullable = false)
  private String position;
  @Column(name = "phone", length = 15, nullable = false)
  private String phone;
  @Column(name = "doc_name", length = 50, nullable = false)
  private String doc_name;
  @Column(name = "doc_number", length = 15, nullable = false)
  private String doc_number;
  @Column(name = "doc_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date doc_date;
  @Column(name = "is_identified", nullable = false)
  private Boolean is_identified;
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "citizenship_code")
  private Countries countries;
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "office_id")
  private Office office;
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "doc_code")
  private Docs docs;


  public User() {

  }

  /**
   * @param id              id
   * @param officeId        officeId
   * @param firstName       firstName
   * @param secondName      secondName
   * @param middleName      middleName
   * @param position        position
   * @param phone           phone
   * @param docCode         docCode
   * @param docName         docName
   * @param docNumber       docNumber
   * @param docDate         docDate
   * @param citizenshipCode citizenshipCode
   * @param isIdentified    isIdentified
   */
  public User(Long id,
              Long officeId,
              String firstName,
              String secondName,
              String middleName,
              String position,
              String phone,
              Integer docCode,
              String docName,
              String docNumber,
              Date docDate,
              Integer citizenshipCode,
              Boolean isIdentified) {
    this.id = id;
    this.first_name = firstName;
    this.second_name = secondName;
    this.middle_name = middleName;
    this.position = position;
    this.phone = phone;
    this.doc_name = docName;
    this.doc_number = docNumber;
    this.doc_date = docDate;
    this.is_identified = isIdentified;
  }

  /**
   * @param officeId        officeId
   * @param firstName       firstName
   * @param secondName      secondName
   * @param middleName      middleName
   * @param position        position
   * @param phone           phone
   * @param docCode         docCode
   * @param docName         docName
   * @param docNumber       docNumber
   * @param docDate         docDate
   * @param citizenshipCode citizenshipCode
   * @param isIdentified    isIdentified
   */
  public User(Office officeId,
              String firstName,
              String secondName,
              String middleName,
              String position,
              String phone,
              Docs docCode,
              String docName,
              String docNumber,
              Date docDate,
              Countries citizenshipCode,
              Boolean isIdentified) {
    this.office = officeId;
    this.docs = docCode;
    this.countries = citizenshipCode;
    this.first_name = firstName;
    this.second_name = secondName;
    this.middle_name = middleName;
    this.position = position;
    this.phone = phone;
    this.doc_name = docName;
    this.doc_number = docNumber;
    this.doc_date = docDate;
    this.is_identified = isIdentified;
  }

  public void setOffice(Office office) {
    this.office = office;
  }

  public Docs getDocs() {
    return this.docs;
  }

  public void setDocs(Docs docs) {
    this.docs = docs;
  }

  public void setUpdVal(UserView userView) {
    this.first_name = userView.first_name;
    this.second_name = userView.second_name;
    this.middle_name = userView.middle_name;
    this.position = userView.position;
    this.phone = userView.phone;
  }

  public Long getId() {
    return id;
  }

  public String getPhone() {
    return phone;
  }

  public Date getDoc_date() {
    return doc_date;
  }

  public Boolean isIs_identified() {
    return is_identified;
  }

  public String getDoc_name() {
    return docs.getName();
  }

  public String getDoc_number() {
    return doc_number;
  }

  public String getFirst_name() {
    return first_name;
  }

  public String getMiddle_name() {
    return middle_name;
  }

  public String getPosition() {
    return position;
  }

  public String getSecond_name() {
    return second_name;
  }

  @Override
  public String toString() {
    return super.toString();
  }

  public Long getOfficeID() { return office.getOrg_id();}

  public Integer getCountries() {
    return countries.getCode();
  }

  public void setCountries(Countries countries) {
    this.countries = countries;
  }
}