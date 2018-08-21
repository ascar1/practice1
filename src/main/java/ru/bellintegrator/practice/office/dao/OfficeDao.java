package ru.bellintegrator.practice.office.dao;

/*
        DAO для работы с Organization
 */
import java.util.List;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

public interface OfficeDao {

  List<Office> all();

  Office loadById(Long id);

  List<Office> getFilter(Long orgId, String name, Boolean isActive);

  void save(Office office);

  void update(OfficeView officeView);

}
