package ru.bellintegrator.practice.organization.dao;

/*
        DAO для работы с Organization
 */

import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

public interface OrganizationDao {

    List<Organization> all();

    Organization  loadByID (Long id);

    Organization loadByName (String name);

    List<Organization> filter(String name, String inn, Boolean isactive);

    void save(Organization organization);

    void update (Organization organization);
}
