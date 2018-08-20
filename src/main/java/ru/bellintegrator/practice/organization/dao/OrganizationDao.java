package ru.bellintegrator.practice.organization.dao;
/*
        DAO для работы с Organization
 */

import java.util.List;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

public interface OrganizationDao {

  List<Organization> all();

  Organization loadById(Long id);

  Organization loadByName(String name);

  List<Organization> filter(String name, String inn, Boolean isactive);

  void save(Organization organization);

  void update(OrganizationView organization);
}
