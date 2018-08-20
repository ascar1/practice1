package ru.bellintegrator.practice.organization.service;

import java.util.List;
import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.organization.view.OrganizationListView;
import ru.bellintegrator.practice.organization.view.OrganizationView;

public interface OrganizationService {
  /*
          добавить организацию
   */
  void add(OrganizationView organization);

  /*
          получить список
   */
  List<OrganizationView> organizaton() throws ExceptionValid;

  /*
          выдать организациб по ID
   */
  OrganizationView getOrganization(String id) throws ExceptionValid;

  /*
          выдать список по фильтру
   */
  List<OrganizationListView> getFilterOrg(String name,
                                          String inn,
                                          Boolean isactive) throws ExceptionValid;

  /*
      Записать организацию
   */
  void save(OrganizationView organizationView) throws ExceptionValid;

  /*
      Обновить организвцию
   */
  void update(OrganizationView organizationView) throws ExceptionValid;
}
