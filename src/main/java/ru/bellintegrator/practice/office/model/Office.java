package ru.bellintegrator.practice.office.model;

import java.util.List;
import javax.persistence.*;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.user.model.User;

@Entity
@Table(name = "office")
public class Office {
  public Office() {

  }

  /**
   * @param id id
   * @param orgId orgId
   * @param name name
   * @param address address
   * @param phone phone
   * @param isActive isActive
   */
  public Office(Long id, Long orgId, String name, String address, String phone, Boolean isActive) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.is_active = isActive;
  }

  /**
   * @param orgId orgId
   * @param name name
   * @param address address
   * @param phone phone
   * @param isActive isActive
   */
  public Office(Organization orgId, String name, String address, String phone, Boolean isActive) {
    this.org = orgId;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.is_active = isActive;
  }

  @Id
  @GeneratedValue
  @Column
  private Long id;
  @Version
  private Integer version;
  @Column(name = "name", length = 50, nullable = false)
  private String name;
  @Column(name = "address", length = 100, nullable = false)
  private String address;
  @Column(name = "phone", length = 15, nullable = false)
  private String phone;
  @Column(name = "is_active", nullable = false)
  private Boolean is_active;

  @OneToMany(mappedBy = "office")
  private List<User> users;

  public void setUsers(User user) {
    this.users.add(user);
    user.setOffice(this);
  }

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "org_id")
  private Organization org;

  public void setOrg(Organization org) {
    this.org = org;
  }

  /**
   * @param officeView обновляемый обьект
   */
  public void setUpdVal(OfficeView officeView) {
    this.name = officeView.name;
    this.address = officeView.address;
    this.phone = officeView.phone;
  }

  public Long getId() {
    return id;
  }

  public Long getOrg_id() {
    return this.org.getId();
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public Boolean isIs_active() {
    return is_active;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
