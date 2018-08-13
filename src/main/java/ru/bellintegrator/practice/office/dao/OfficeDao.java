package ru.bellintegrator.practice.office.dao;

/*
        DAO для работы с Organization
 */

import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

public interface OfficeDao {

    List<Office> all();

    Office loadByID (long id);

    List<Office> getFilter(Long orgId, String name , Boolean is_active);

    void save(Office organization);

    void update (OfficeView organization);

}
