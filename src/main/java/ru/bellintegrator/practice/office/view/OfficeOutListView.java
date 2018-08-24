package ru.bellintegrator.practice.office.view;

public class OfficeOutListView {
  public OfficeOutListView(){}

  public OfficeOutListView(Long id, String name, Boolean isActive) {
    this.id = id;
    this.name = name;
    this.is_active = isActive;
  }

  public Long id;
  public String name;
  public Boolean is_active;
}
