package ru.bellintegrator.practice.docs.model;

import ru.bellintegrator.practice.user.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="doc")
public class Docs {
    public Docs (){}

    @Id
    @Column(name = "code")
    private Integer code;

    @Version
    private Integer version;
    @Column(name = "name",length = 50,nullable = false)
    private String name;

    @OneToMany(mappedBy = "docs")
    private List<User> user;

    public String getName(){return name;}

    public Integer getCode() {return code;}

}
