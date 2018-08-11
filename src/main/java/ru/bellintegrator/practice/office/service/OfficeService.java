package ru.bellintegrator.practice.office.service;

import ru.bellintegrator.practice.error.ExceptionValid;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeOutListView;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

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
    Office getByID(Long id);
    /*
            выдать список по фильтру
     */
    List<OfficeOutListView> getFilter(Long org_id, String name, Boolean isactive) throws ExceptionValid;
    /*
        Записать организацию
     */
    void save (OfficeView officeView) throws ExceptionValid;
    /*
        Обновить организвцию
     */
    void update (OfficeView officeView) throws ExceptionValid;

}
