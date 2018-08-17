package ru.bellintegrator.practice.countries.model;

import io.swagger.models.auth.In;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COUNTRY")
public class Countries {
    public Countries(){}
    @Id
    @Column(name ="code")
    private Integer code;

    @Version
    private Integer version;
    @Column (name = "name", length = 50)
    private String name;

    @OneToMany(mappedBy = "countries")
    private List<User> user;
    private void setUser (User user){
        this.user.add(user);
        user.setCountries(this);
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
