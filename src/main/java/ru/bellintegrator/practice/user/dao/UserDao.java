package ru.bellintegrator.practice.user.dao;

import java.util.List;

import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;


public interface UserDao {
  List<User> all();

  UserView loadById(Long id);

  List<User> getFilter(Long officeid,
                       String firstname,
                       String secondName,
                       String middleName,
                       String position,
                       Long docCode,
                       Integer citizenshipCode);

  void save(User user);

  void update(UserView user);
}
