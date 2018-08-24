package ru.bellintegrator.practice.user.controller;

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
import ru.bellintegrator.practice.office.controller.OfficeController;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OfficeController.class)
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private UserService userService;

  private List<UserView> getTestListView (){
    List<UserView> list = new ArrayList<UserView>();
    list.add(new UserView(1L,
            1L,
            "firstName",
            "secondName",
            "middleName",
            "position",
            "phone",
            1L,
            "docName",
            "docNumber",
            new Date(1,1,2018),
            1,
            true));
    list.add(new UserView(2L,
            2L,
            "firstName",
            "secondName",
            "middleName",
            "position",
            "phone",
            1L,
            "docName",
            "docNumber",
            new Date(2,2,2018),
            1,
            true));
  return list;
  }

  private UserView getOrganizationTest (){
        return new UserView(1L,
                1L,
                         "firstName",
                      "secondName",
                      "middleName",
                            "position",
                                "phone",
                1L,
                            "docName",
                        "docNumber",
                new Date(1,1,2018),
                                1,
                              true);
  }

  @Test
  public void metodAll()throws Exception {
  when(userService.user()).thenReturn(getTestListView());
    this.mockMvc.perform(MockMvcRequestBuilders.get("/user/all"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", Matchers.is(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(2)))
            .andDo(print());
  }

  @Test
  public void getOrgTest ()throws Exception  {
  when(userService.getById("1")).thenReturn(getOrganizationTest());
  this.mockMvc.perform(MockMvcRequestBuilders.get("/user/?id=1"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
            .andDo(print());
  }

}