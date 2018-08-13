package ru.bellintegrator.practice.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeOutListView;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OfficeServiseImpl implements OfficeService{
    private final OfficeDao dao;

    @Autowired
    public OfficeServiseImpl(OfficeDao dao){this.dao = dao;}

    @Override
    @Transactional
    public void add(OfficeView officeView){

    }
    @Override
    @Transactional(readOnly = false)
    public List<OfficeOutListView> office(){
        List<Office> all = dao.all();

        return all.stream()
                .map(mapOffice())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Office getByID(Long id){
        Office office = dao.loadByID(id);
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
        Office office = new Office (officeView.org_id, officeView.name,officeView.address, officeView.phone,officeView.is_active);
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
        if (name == "") {
            exception.addError("Name if empty");
        }
        checAndTrowException(exception);
    }

    private void saveValidation (OfficeView office) throws ExceptionValid{
        ExceptionValid exception = new ExceptionValid();
        if (office.id == null) {
            exception.addError( "org_id is empty");
        }
        if (office.name.isEmpty()) {
            exception.addError( "name is empty");
        }
        if (office.address.isEmpty()) {
            exception.addError( "address id empty");
        }
        if (office.is_active == null) {
            exception.addError("is_active is epmty");
        }
        checAndTrowException(exception);
    }

    private void updateValidation (OfficeView office) throws ExceptionValid{
        ExceptionValid exception = new ExceptionValid();
        if (office.id == null) {
            exception.addError( "org_id is empty");
        }
        if (office.name.isEmpty()) {
            exception.addError( "name is empty");
        }
        if (office.address.isEmpty()) {
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
