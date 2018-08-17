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
        //this.office_id = office_id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.middle_name = middle_name;
        this.position = position;
        this.phone = phone;
        //this.doc_code = doc_Code;
        this.doc_name = doc_name;
        this.doc_number = doc_number;
        this.doc_date = doc_date;
        //this.citizenship_code = citizenship_code;
        this.is_identified = is_identified;
    }

    public User(Office office_id, String first_name,String second_name, String middle_name,String position, String phone, Docs doc_Code, String doc_name, String doc_number, Date doc_date, Countries citizenship_code, Boolean is_identified){

        this.office = office_id;
        this.docs = doc_Code;
        this.countries = citizenship_code;

        this.first_name = first_name;
        this.second_name = second_name;
        this.middle_name = middle_name;
        this.position = position;
        this.phone = phone;

        this.doc_name = doc_name;
        this.doc_number = doc_number;
        this.doc_date = doc_date;
        this.is_identified = is_identified;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Version
    private Integer Version;
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
    @Column (name = "doc_name", length = 50,nullable = false)
    private String doc_name;
    @Column (name = "doc_number", length = 15,nullable = false)
    private String doc_number;
    @Column(name = "doc_date" , nullable = false)
    @Temporal(TemporalType.DATE)
    private Date doc_date;
    @Column(name = "is_identified" , nullable = false)
    private Boolean is_identified;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "citizenship_code")
    private Countries countries;
    public void setCountries (Countries countries){
        this.countries = countries;
    }

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "office_id")
    private Office office;
    public void setOffice (Office office){
        this.office = office;
    }

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "doc_code")
    private Docs docs ;
    public void setDocs(Docs docs){
        this.docs = docs;
    }

    public Docs getDocs(){
        return this.docs;

    }


    public void SetUpdVal (UserView userView){
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
}