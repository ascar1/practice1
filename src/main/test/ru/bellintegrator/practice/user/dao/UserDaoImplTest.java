package ru.bellintegrator.practice.user.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.countries.dao.CountriesDaoImpl;
import ru.bellintegrator.practice.docs.dao.DocsDaoImpl;
import ru.bellintegrator.practice.office.dao.OfficeDaoImpl;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class UserDaoImplTest {
    @Autowired
    UserDaoImpl userDao;
    @Autowired
    private CountriesDaoImpl countriesDao ;
    @Autowired
    private DocsDaoImpl docsDao ;
    @Autowired
    private OfficeDaoImpl officeDao;

    @Test
    public void save() {
        User user = new User (officeDao.loadById(new Long(1)) , "first_name","second_name","middle_name","position","+7495123456", docsDao.loadById(new Long (10)) ,"doc_name","doc_number",new Date(1,1,2017),countriesDao.loadById(643) ,true);
        userDao.save(user);
        Assert.assertNotNull(userDao.getFilter(new Long(1),"first_name","second_name","position","position",null,null));
    }

    @Test
    public void update() {
        UserView userView = new UserView();
        userView.id = new Long(1);
        userView.first_name = "new name";
        userDao.update(userView);
        if (!userView.equals(userDao.loadById(new Long(1)))){
            Assert.assertFalse(false);
        }
    }
}