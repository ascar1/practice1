package ru.bellintegrator.practice.docs.dao;

import ru.bellintegrator.practice.docs.model.Docs;

import java.util.List;

public interface DocsDao {
    List<Docs> all();
    Docs loadById (Long id);
    void save (Docs docs);
}
