package ru.bellintegrator.practice.user.model;

import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.practice.countries.model.Countries;
import ru.bellintegrator.practice.docs.model.Docs;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.user.view.UserView;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.PrimitiveIterator;

@Entity
@Table(name = "user")
public class User {
    private static Logger log = LoggerFactory.getLogger(OrganizationDao.class.getName());
    public User(){

    }

    public User (Long id , Long office_id, String first_name, String second_name, String middle_name, String position, String phone, Integer doc_Code, String doc_name, String doc_number, Date doc_date, Integer citizenship_code, Boolean is_identified){
        this.id = id;
        this.office_id = office_id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.middle_name = middle_name;
        this.position = position;
        this.phone = phone;
        this.doc_code = doc_Code;
        this.doc_name = doc_name;
        this.doc_number = doc_number;
        this.doc_date = doc_date;
        this.citizenship_code = citizenship_code;
        this.is_identified = is_identified;
    }

    public User(Long office_id, String first_name,String second_name, String middle_name,String position, String phone, Integer doc_Code, String doc_name, String doc_number, Date doc_date, Integer citizenship_code, Boolean is_identified){
        this.first_name = first_name;
        this.second_name = second_name;
        this.middle_name = middle_name;
        this.position = position;
        this.phone = phone;
        this.doc_code = doc_Code;
        this.doc_name = doc_name;
        this.doc_number = doc_number;
        this.doc_date = doc_date;
        //this.citizenship_code = citizenship_code;
        this.is_identified = is_identified;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Version
    private Integer Version;
    @Column(name = "office_id",insertable= false, updatable=false)
    private Long office_id;
    @Column (name = "first_name", length = 50,nullable = false)
    private String first_name;
    @Column (name = "second_name", length = 50,nullable = false)
    private String second_name;
    @Column (name = "middle_name", length = 50,nullable = false)
    private String middle_name;
    @Column (name = "position", length = 30,nullable = false)
    private String position;
    @Column (name = "phone", length = 15,nullable = false)
    private String phone;
    @Column(name = "doc_code" , nullable = false,insertable= false, updatable=false)
    private Integer doc_code;
    @Column (name = "doc_name", length = 50,nullable = false)
    private String doc_name;
    @Column (name = "doc_number", length = 15,nullable = false)
    private String doc_number;
    @Column(name = "doc_date" , nullable = false)
    @Temporal(TemporalType.DATE)
    private Date doc_date;
    @Column (name = "citizenship_code",nullable = false,insertable= false, updatable=false)
    private Integer citizenship_code;
    @Column(name = "is_identified" , nullable = false)
    private Boolean is_identified;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_code")
    private Docs docs ;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "citizenship_code")
    private Countries countries;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Office office;

    public void SetUpdVal (UserView userView){
        if (!userView.first_name.isEmpty() && !userView.first_name.equals(first_name)){
            this.first_name = userView.first_name;
        }

        if(!userView.second_name.equals(second_name)){
            this.second_name = userView.second_name;
        }
        if(!userView.middle_name.equals(middle_name)){
            this.middle_name = userView.middle_name;
        }
        if(!userView.position.equals(position)){
            this.position = userView.position;
        }
        if(!userView.phone.equals(phone)){
            this.phone = userView.phone;
        }
        if(!userView.doc_Code.equals(doc_code)){
            this.doc_code = userView.doc_Code;
        }
    }

    public Long getId() {
        return id;
    }

    public Long getOffice_id(){
        return office_id;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDoc_date() {
        return doc_date;
    }

    public Integer getDoc_Code() {
        return doc_code;
    }

    public Boolean isIs_identified() {
        return is_identified;
    }

    public Integer getCitizenship_code() {
        return citizenship_code;
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
}