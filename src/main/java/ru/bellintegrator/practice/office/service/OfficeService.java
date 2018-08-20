package ru.bellintegrator.practice.office.service;

import java.util.List;

import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeOutListView;
import ru.bellintegrator.practice.office.view.OfficeView;



public interface OfficeService {
  /*
          добавить организацию
  */
  void add(OfficeView organization);

  /*
          получить список
  */
  List<OfficeOutListView> office();

  /*
            выдать организациб по ID
  */
  Office getById(String id) throws ExceptionValid;

  /*
          выдать список по фильтру
   */
  List<OfficeOutListView> getFilter(Long orgId,
                                    String name,
                                    Boolean isActive) throws ExceptionValid;

  /*
      Записать организацию
   */
  void save(OfficeView officeView) throws ExceptionValid;

  /*
      Обновить организвцию
   */
  void update(OfficeView officeView) throws ExceptionValid;
}
