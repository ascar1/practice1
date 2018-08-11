package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserListView;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;

public interface UserService {
    List<UserView> User ();
    User getByID(long id);
    List<UserListView> getFilter(Long office_id, String first_name, String second_name, String middle_name, String position, Integer doc_Code, String citizenship_code) throws ExceptionValid;
    void save(User user) throws ExceptionValid;
    void update (User user) throws ExceptionValid;

}