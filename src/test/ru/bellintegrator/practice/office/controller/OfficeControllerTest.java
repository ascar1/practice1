package ru.bellintegrator.practice.office.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.organization.controller.OrganizationController;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
/*
@RunWith(SpringRunner.class)
@WebMvcTest(OrganizationController.class)
@WebAppConfiguration(value = "src/main/resources")
@SpringBootTest(classes = {Application.class})
@EnableWebMvc
@DirtiesContext
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OfficeControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private OfficeService officeService;

  @Test
  public void metod() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/all")).andDo(print());
  }
}