package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;

public interface UserDao {
    List<User> all();
    User loadByID(Long id);
    List<User> GetFilter(Long officeid, String firstname,String second_name, String middle_name,String position, Long doc_code , Integer citizenship_code  );

    void save (User user);
    void update(UserView user);

}
