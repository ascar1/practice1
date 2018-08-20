package ru.bellintegrator.practice.docs.model;

import java.util.List;
import javax.persistence.*;
import ru.bellintegrator.practice.user.model.User;

@Entity
@Table(name = "doc")
public class Docs {
  public Docs() {
  }

  @Id
  @Column(name = "code")
  private Long code;

  @Version
  private Integer version;
  @Column(name = "name", length = 50, nullable = false)
  private String name;

  @OneToMany(mappedBy = "docs")
  private List<User> user;

  public void setUser(User user) {
    this.user.add(user);
    user.setDocs(this);
  }

  public String getName() {
    return name;
  }

  public Long getCode() {
    return code;
  }
}
