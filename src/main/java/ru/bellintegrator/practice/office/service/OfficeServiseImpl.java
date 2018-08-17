package ru.bellintegrator.practice.office.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.StringUtils;
import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.office.controller.OfficeController;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeOutListView;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OfficeServiseImpl implements OfficeService{
    private final OfficeDao dao;

    private static Logger log = LoggerFactory.getLogger(OfficeController.class.getName());

    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    public OfficeServiseImpl(OfficeDao dao){this.dao = dao;}

    @Override
    @Transactional
    public void add(OfficeView officeView){

    }
    @Override
    public List<OfficeOutListView> office(){
        List<Office> all = dao.all();

        return all.stream()
                .map(mapOffice())
                .collect(Collectors.toList());
    }

    @Override
    public Office getByID (String id) throws ExceptionValid{

        validationID(id);
        Long _id = new Long(id);
        Office office = dao.loadByID(_id);
        return office;
    }

    @Override
    public List<OfficeOutListView> getFilter(Long org_id, String Name, Boolean IsActive) throws ExceptionValid {
        validation(Name);
        List<Office> Filter = dao.getFilter(org_id,Name,IsActive);
        return Filter.stream()
                .map(mapOffice())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save (OfficeView officeView) throws ExceptionValid {
        saveValidation(officeView);
        Office office = new Office (organizationDao.loadByID(officeView.org_id), officeView.name,officeView.address, officeView.phone,officeView.is_active);
        dao.save(office);
    }

    @Override
    @Transactional
    public void update (OfficeView officeView)throws ExceptionValid{
        updateValidation(officeView);
        dao.update(officeView);
    }

    private void validation (String name ) throws ExceptionValid {
        ExceptionValid exception = new ExceptionValid();
        if (StringUtils.isEmpty(name)) {
            exception.addError("Name is empty");
        }
        checAndTrowException(exception);
    }

    private void validationID (String val) throws ExceptionValid {
        ExceptionValid exception = new ExceptionValid();
        if (StringUtils.isEmpty(val)){
            exception.addError("ID is empty");
        }
        if (!NumberUtils.isDigits(val)){
            exception.addError("ID is not digit");
        }
        checAndTrowException(exception);
    }


    private void idValidation (String Id) throws ExceptionValid{

    }

    private void saveValidation (OfficeView office) throws ExceptionValid{
        ExceptionValid exception = new ExceptionValid();
        if (office.org_id == null) {
            exception.addError( "org_id is empty");
        }
        if (StringUtils.isEmpty(office.name)) {
            exception.addError( "name is empty");
        }
        if (StringUtils.isEmpty(office.address)) {
            exception.addError( "address id empty");
        }
        if ( office.is_active == null) {
            exception.addError("is_active is epmty");
        }
        checAndTrowException(exception);
    }

    private void updateValidation (OfficeView office) throws ExceptionValid{
        ExceptionValid exception = new ExceptionValid();
        if (office.id == null) {
            exception.addError( "org_id is empty");
        }
        if (StringUtils.isEmpty(office.name)) {
            exception.addError( "name is empty");
        }
        if (StringUtils.isEmpty(office.address)) {
            exception.addError( "address id empty");
        }
        if (office.is_active == null) {
            exception.addError("is_active is epmty");
        }
        checAndTrowException(exception);
    }

    private void checAndTrowException (ExceptionValid exceptionValid)throws ExceptionValid {
        if (!exceptionValid.errors.isEmpty()){
            throw exceptionValid;
        }
    }

    private Function<Office,OfficeOutListView> mapOffice(){
        return p -> {
            OfficeOutListView view = new OfficeOutListView();
            view.id =  p.getId();
            view.name = p.getName();
            view.is_active = p.isIs_active();
            return view;
        };
    }
}
