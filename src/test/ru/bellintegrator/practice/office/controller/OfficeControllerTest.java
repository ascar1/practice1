package ru.bellintegrator.practice.office.controller;

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
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeOutListView;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OfficeController.class)
public class OfficeControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private OfficeService officeService;

  private List<OfficeOutListView> getOfficeListView (){
    List<OfficeOutListView> list = new ArrayList<OfficeOutListView>();
    list.add(new OfficeOutListView(1L,"name_1",true));
    list.add(new OfficeOutListView(2L,"name_2",true));
    return list;
  }

  private OfficeView getOfficeViewTest(){
    return new OfficeView(1L, 1L,"name","fullName","phone",true);
  }

  @Test
  public void metodAll() throws Exception {
    when(officeService.office()).thenReturn(getOfficeListView());
    this.mockMvc.perform(MockMvcRequestBuilders.get("/office/all"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", Matchers.is(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(2)))
            .andDo(print());

  }

  @Test
  public void metodGetId() throws Exception{
    when(officeService.getById("1")).thenReturn(getOfficeViewTest());
    this.mockMvc.perform(MockMvcRequestBuilders.get("/office/?id=1"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
            .andDo(print());
  }
}
