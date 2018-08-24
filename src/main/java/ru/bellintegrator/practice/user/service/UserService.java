package ru.bellintegrator.practice.user.service;

import java.util.List;

import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserListView;
import ru.bellintegrator.practice.user.view.UserView;

public interface UserService {
  List<UserView> user();

  UserView getById(String id) throws ExceptionValid;

  List<UserListView> getFilter(Long officeId,
                               String firstName,
                               String secondName,
                               String middleName,
                               String position,
                               Long docCode,
                               Integer citizenshipCode) throws ExceptionValid;

  void save(UserView userView) throws ExceptionValid;

  void update(UserView userView) throws ExceptionValid;
}
