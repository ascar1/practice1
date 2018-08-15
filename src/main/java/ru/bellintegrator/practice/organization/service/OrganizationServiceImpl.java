package ru.bellintegrator.practice.organization.service;


import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationListView;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class OrganizationServiceImpl implements  OrganizationService  {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final OrganizationDao dao;


    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao){
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add (OrganizationView organizationView){

    }

    @Override
    public List<OrganizationView> organizaton() throws ExceptionValid{
        List<Organization> all = dao.all();
        return all.stream()
                .map(mapOrganizationFull())
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationView getOrganization(String id)throws ExceptionValid{
        validationID(id);
        Long _id = new Long(id);
        return  new OrganizationView(dao.loadByID(_id));
    }

    @Override
    public List<OrganizationListView> getFilterOrg(String name, String inn, Boolean isactive) throws ExceptionValid {
        validation(name);
        List<Organization> Filter = dao.filter(name,inn,isactive);

        return Filter.stream()
                .map(mapOrganization())
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void save (OrganizationView organizationView) throws ExceptionValid{
        saveValidation(organizationView);
        Organization organization = new Organization(organizationView.name,organizationView.full_name,organizationView.inn,organizationView.kpp,organizationView.address,organizationView.phone,organizationView.is_active);
        dao.save(organization);
    }

    @Override
    @Transactional
    public void update (OrganizationView organizationView) throws ExceptionValid{
        updateValidation(organizationView);
        dao.update(organizationView);
    }

    private void validation  (String name) throws ExceptionValid {
        ExceptionValid exception = new ExceptionValid();
        if (StringUtils.isEmpty(name)) {
            exception.addError("Name if empty");
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
    private void saveValidation (OrganizationView organization) throws ExceptionValid {
        ExceptionValid exception = new ExceptionValid();
        if (StringUtils.isEmpty(organization.name)){
            exception.addError("name is empty");
        }
        if(StringUtils.isEmpty(organization.full_name)){
            exception.addError("full_name is empty");
        }
        if (StringUtils.isEmpty(organization.inn)){
            exception.addError("inn is empty");
        }
        if (StringUtils.isEmpty(organization.kpp)){
            exception.addError("kpp is empty");
        }
        if (StringUtils.isEmpty(organization.address)){
            exception.addError("address is empty");
        }
        checAndTrowException(exception);
    }
    private void updateValidation(OrganizationView organization) throws ExceptionValid {
        ExceptionValid exception = new ExceptionValid();
        if (organization.id == null){
            exception.addError("id is empty");
        }
        if (StringUtils.isEmpty(organization.name)){
            exception.addError("name is empty");
        }
        if(StringUtils.isEmpty(organization.full_name)){
            exception.addError("full_name is empty");
        }
        if (StringUtils.isEmpty(organization.inn)){
            exception.addError("inn is empty");
        }
        if (StringUtils.isEmpty(organization.kpp)){
            exception.addError("kpp is empty");
        }
        if (StringUtils.isEmpty(organization.address)){
            exception.addError("address is empty");
        }
        checAndTrowException(exception);
    }
    private void checAndTrowException (ExceptionValid exceptionValid)throws ExceptionValid {
        if (!exceptionValid.errors.isEmpty()){
            throw exceptionValid;
        }
    }
    private Function<Organization, OrganizationListView> mapOrganization(){
        return p -> {
            OrganizationListView view = new OrganizationListView();
            view.id = p.getId();
            view.name = p.getName();
            view.is_active = p.isIs_active();
            log.debug(view.toString());
            return view;
        };
    }
    private Function<Organization, OrganizationView> mapOrganizationFull(){
        return p -> {
            OrganizationView view = new OrganizationView();
            view.id = Long.valueOf(p.getId());
            view.name = p.getName();
            view.full_name = p.getFull_name();
            view.inn = p.getInn();
            view.kpp = p.getKpp();
            view.address = p.getAddress();
            view.phone = p.getPhone();
            view.is_active = p.isIs_active();
            log.debug(view.toString());
            return view;
        };
    }


}
