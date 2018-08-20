package ru.bellintegrator.practice.user.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ru.bellintegrator.practice.countries.dao.CountriesDao;
import ru.bellintegrator.practice.docs.dao.DocsDao;
import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserListView;
import ru.bellintegrator.practice.user.view.UserView;

@Service
public class UserServiceImpl implements UserService {
  private static Logger log = LoggerFactory.getLogger(OrganizationDao.class.getName());
  private final UserDao dao;
  @Autowired
  private CountriesDao countriesDao;
  @Autowired
  private DocsDao docsDao;
  @Autowired
  private OfficeDao officeDao;

  @Autowired
  public UserServiceImpl(UserDao dao) {
    this.dao = dao;
  }


  @Override
  @Transactional(readOnly = true)
  public List<UserView> user() {
    List<User> all = dao.all();
    return all.stream()
            .map(mapUser())
            .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public User getById(String id) throws ExceptionValid {
    validationId(id);
    return dao.loadById(new Long(id));
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserListView> getFilter(Long officeId,
                                      String firstName,
                                      String secondName,
                                      String middleName,
                                      String position,
                                      Long docCode,
                                      Integer citizenshipCode) {
    List<User> filter = dao.getFilter(officeId,
            firstName,
            secondName,
            middleName,
            position,
            docCode,
            citizenshipCode);
    return filter.stream()
            .map(mapListUser())
            .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void save(UserView userView) throws ExceptionValid {
    saveValidation(userView);
    User user = new User(officeDao.loadById(userView.office_id),
            userView.first_name,
            userView.second_name,
            userView.middle_name,
            userView.position,
            userView.phone,
            docsDao.loadById(userView.doc_Code),
            userView.doc_name,
            userView.doc_number,
            userView.doc_date,
            countriesDao.loadById(userView.citizenship_code),
            userView.is_identified);
    dao.save(user);
  }

  @Override
  @Transactional
  public void update(UserView userView) throws ExceptionValid {
    updateValidation(userView);
    dao.update(userView);
  }

  private void validation(Integer id) throws ExceptionValid {
    ExceptionValid exception = new ExceptionValid();
    if (id == null) {
      exception.addError("office_id if empty");
    }
    checAndTrowException(exception);
  }

  private void validationId(String val) throws ExceptionValid {
    ExceptionValid exception = new ExceptionValid();
    if (StringUtils.isEmpty(val)) {
      exception.addError("ID is empty");
    }
    if (!NumberUtils.isDigits(val)) {
      exception.addError("ID is not digit");
    }
    checAndTrowException(exception);
  }

  private void saveValidation(UserView user) throws ExceptionValid {
    ExceptionValid exception = new ExceptionValid();
    if (user.office_id == null) {
      exception.addError("Office_id if empty");
    }
    if (StringUtils.isEmpty(user.first_name)) {
      exception.addError("First_name if empty");
    }
    if (StringUtils.isEmpty(user.position)) {
      exception.addError("Position if empty");
    }
    checAndTrowException(exception);
  }

  private void updateValidation(UserView user) throws ExceptionValid {
    ExceptionValid exception = new ExceptionValid();
    if (user.id == null) {
      exception.addError("ID if empty");
    }
    if (StringUtils.isEmpty(user.first_name)) {
      exception.addError("First_name if empty");
    }
    if (StringUtils.isEmpty(user.position)) {
      exception.addError("Position if empty");
    }
    checAndTrowException(exception);
  }

  private void checAndTrowException(ExceptionValid exceptionValid) throws ExceptionValid {
    if (!exceptionValid.errors.isEmpty()) {
      throw exceptionValid;
    }
  }

  private Function<User, UserView> mapUser() {
    return p -> {
      UserView view = new UserView();
      view.id = p.getId();
      view.first_name = p.getFirst_name();
      view.second_name = p.getSecond_name();
      view.middle_name = p.getMiddle_name();
      view.phone = p.getPhone();
      view.doc_name = p.getDoc_name();
      view.is_identified = p.isIs_identified();
      return view;
    };
  }

  private Function<User, UserListView> mapListUser() {
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

