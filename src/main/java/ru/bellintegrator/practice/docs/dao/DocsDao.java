package ru.bellintegrator.practice.docs.dao;

import java.util.List;
import ru.bellintegrator.practice.docs.model.Docs;

public interface DocsDao {
  List<Docs> all();

  Docs loadById(Long id);

  void save(Docs docs);
}
