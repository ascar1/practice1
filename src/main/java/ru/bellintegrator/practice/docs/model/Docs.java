package ru.bellintegrator.practice.docs.model;

import ru.bellintegrator.practice.user.model.User;

import javax.persistence.*;

@Entity
@Table(name="doc")
public class Docs {
    public Docs (){}
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Version
    private Integer version;
    @Column(name = "name",length = 50,nullable = false)
    private String name;
    @Column(name = "code",  nullable = false)
    private Integer code;

    /*private User user;
    @OneToOne (mappedBy = "getDocs")
    private User getUser ;
*/
    public String getName(){return name;}
    public Long getId (){return id;}
    public Integer getCode() {return code;}

}
