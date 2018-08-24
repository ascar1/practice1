package ru.bellintegrator.practice.organization.controller;


import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(OrganizationController.class)
public class OrganizationControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private OrganizationService organizationService;

  private List<OrganizationView> getTestData (){
    List<OrganizationView> list = new ArrayList<OrganizationView>();
    list.add(new OrganizationView(1L,"name_1","Full_name_1","Inn_1","kpp_1","address_1","phone_1",true));
    list.add(new OrganizationView(2L,"name_2","Full_name_2","inn_2","kpp_2","address_2","phone_2",false));
    return list;
  }
  private OrganizationView getTestOrganizationView(){
    return new OrganizationView(1L,"name_1","Full_name_1","Inn_1","kpp_1","address_1","phone_1",true);
  }

  @Test
  public void metod() throws Exception {
    when(organizationService.getOrganization("1")).thenReturn(getTestOrganizationView());
    this.mockMvc.perform(MockMvcRequestBuilders.get("/organization/?id=1"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
            .andDo(print());
  }

  @Test
  public void metodAll() throws Exception {
    when(organizationService.organizaton()).thenReturn(getTestData());
    this.mockMvc.perform(MockMvcRequestBuilders.get("/organization/all"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", Matchers.is(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(2)))
            .andDo(print());
  }
}