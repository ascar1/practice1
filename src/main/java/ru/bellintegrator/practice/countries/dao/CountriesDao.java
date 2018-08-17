package ru.bellintegrator.practice.countries.dao;

import ru.bellintegrator.practice.countries.model.Countries;

import java.util.List;

public interface CountriesDao {
    List<Countries> all();
    Countries loadById (Integer id);
    void save(Countries countries);
}
