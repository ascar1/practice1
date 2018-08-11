package ru.bellintegrator.practice.countries.dao;

import ru.bellintegrator.practice.countries.model.Countries;

import java.util.List;

public interface CountriesDao {
    List<Countries> all();
    Countries loadById (Long id);
    void save(Countries countries);
}
