package ru.bellintegrator.practice.office.dao;

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
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.organization.dao.OrganizationDaoImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OfficeDaoImplTest {
    @Autowired
    private OfficeDaoImpl officeDao;
    @Autowired
    private OrganizationDaoImpl organizationDao;
    @Test
    public void save() {
        Office office = new Office( organizationDao.loadById(1L),"Office New" , "Russia" , "+7495123456", true);
        officeDao.save(office);
        Assert.assertNotNull(officeDao.getFilter(1L,"Office New", true));
    }

    @Test
    public void update() {
        //Office office = new Office(new Long(1),new Long(1),,true);
        OfficeView officeView = new OfficeView();
        officeView.id = 1L;
        officeView.org_id = 1L;
        officeView.name = "New Name";
        officeView.address = "new adr" ;
        officeView.phone = "+78123456";
        officeView.is_active = true;
        officeDao.update(officeView);

        Office organizationView_ = officeDao.loadById(1L);
        if (!organizationView_.equals(officeView)){
            Assert.assertFalse(false);
        }
    }
}