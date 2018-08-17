package ru.bellintegrator.practice.organization.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationListView;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OrganizationDaoImplTest {
    @Autowired
    OrganizationDaoImpl organizationDao;
    @Test
    public void save() {
        Organization organization = new Organization("name","full_name","12345678","123456","Ufa Repablic Bashkortostan","+7927123456",true);
        organizationDao.save(organization);
        List<Organization> organizationList = organizationDao.filter("name","12345678",true);
        Assert.assertNotNull(organizationList);
    }

    @Test
    public void update() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.id = new Long (1);
        organizationView.name = "name";
        organizationView.full_name = "full_name";
        organizationView.inn = "12345678";
        organizationView.kpp = "123456";
        organizationView.address = "Ufa Repablic Bashkortostan";
        organizationView.is_active = true;
        organizationDao.update(organizationView);

        Organization organizationView_ = organizationDao.loadByID(new Long(1));
        if (!organizationView.equals(organizationView_)){
            Assert.assertFalse(false);
        }
    }
}