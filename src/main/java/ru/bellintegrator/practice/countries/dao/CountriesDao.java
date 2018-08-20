package ru.bellintegrator.practice.countries.dao;

import java.util.List;
import ru.bellintegrator.practice.countries.model.Countries;


public interface CountriesDao {
  List<Countries> all();

  Countries loadById(Integer id);

  void save(Countries countries);
}
