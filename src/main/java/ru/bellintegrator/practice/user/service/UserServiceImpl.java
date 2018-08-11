package ru.bellintegrator.practice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserListView;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao dao;

    @Autowired
    public UserServiceImpl (UserDao dao){
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserView> User(){
        List<User> all = dao.all();
        return all.stream()
                .map(mapUser())
                .collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public User getByID(long id){
        User user = dao.loadByID(id);
        return user;
    }
    @Override
    @Transactional(readOnly = true)
    public List<UserListView> getFilter(Long office_id, String first_name, String second_name, String middle_name, String position, Integer doc_Code, String citizenship_code) throws ExceptionValid{
        List<User> Filter = dao.GetFilter(office_id,first_name,second_name,middle_name,position,doc_Code,citizenship_code);
        return Filter.stream()
                .map(mapListUser())
                .collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = false)
    public void save(User user) throws ExceptionValid {
        saveValidation(user);
        dao.save(user);
    }
    @Override
    @Transactional(readOnly = false)
    public void update (User user) throws ExceptionValid{
        updateValidation(user);
        dao.update(user);
    }

    private void validation  (Integer id) throws ExceptionValid {
        ExceptionValid exception = new ExceptionValid();
        if (id == null) {
            exception.addError("office_id if empty");
        }
        checAndTrowException(exception);
    }

    private void saveValidation  (User user) throws ExceptionValid {
        ExceptionValid exception = new ExceptionValid();
        if (user.getOffice_id() == null){
            exception.addError("Office_id if empty");
        }
        if (user.getFirst_name() == "") {
            exception.addError("First_name if empty");
        }
        if (user.getPosition() == "") {
            exception.addError("Position if empty");
        }
        checAndTrowException(exception);
    }

    private void updateValidation  (User user) throws ExceptionValid {
        ExceptionValid exception = new ExceptionValid();
        if (user.getId() == null){
            exception.addError("ID if empty");
        }
        if (user.getFirst_name() == "") {
            exception.addError("First_name if empty");
        }
        if (user.getPosition() == "") {
            exception.addError("Position if empty");
        }
        checAndTrowException(exception);
    }

    private void checAndTrowException (ExceptionValid exceptionValid)throws ExceptionValid {
        if (!exceptionValid.errors.isEmpty()){
            throw exceptionValid;
        }
    }
    private Function<User,UserView> mapUser (){
        return p -> {
            UserView view = new UserView();
            view.id = p.getId();
            view.first_name = p.getFirst_name();
            view.second_name = p.getSecond_name();
            view.middle_name = p.getMiddle_name();
            view.phone = p.getPhone();
            view.is_identified = p.isIs_identified();
            return view;
        };
    }
    private Function<User, UserListView> mapListUser (){
        return p -> {
            UserListView view = new UserListView();
            view.id = p.getId();
            view.first_name = p.getFirst_name();
            view.second_name = p.getSecond_name();
            view.middle_name = p.getMiddle_name();
            view.position = p.getPosition();
            return view;
        };
    }
}

