package ru.bellintegrator.practice.organization.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(OrganizationController.class)
@WebAppConfiguration(value = "src/main/resources")
@SpringBootTest(classes = {Application.class})
@EnableWebMvc
@DirtiesContext
public class OrganizationControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private OrganizationService service;

  @Test
  public void metod() throws Exception {
    OrganizationView organization = new OrganizationView();
    when(service.getOrganization("1")).thenReturn(organization);
    this.mockMvc.perform(get("/organization/?id=1ы").contentType(MediaType.APPLICATION_JSON_UTF8))
            //.andExpect(content().contentType(contentType))
            .andExpect(status().isOk())
            .andDo(print());

    //.andExpect(jsonPath("$", hasSize(1)))
    //.andExpect(jsonPath("$[0].id",is(1)))
    //.andReturn().getResponse().getContentAsString();
    //.andExpect(jsonPath("$.name",is("Рога и копыта")));

        /*this.mockMvc.perform(get("/organization/?id=1s"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].id",is(1)));*/

  }


  @Test
  public void metodAll() throws Exception {
    this.mockMvc.perform(get("/organization/all"))
            //.accept(contentType)
            .andExpect(status().isOk())
            .andDo(print());
    //.andExpect(content().contentType(contentType));
    // .andExpect(jsonPath("$",hasSize(2)));
    //.andExpect(jsonPath("$[0].id",is(1)));


                /*.andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.id",is(1)));*/

    //.andExpect(jsonPath("$.id",is(1)));
  }
}

    /*@Autowired
    WebApplicationContext webAppContext;
    @Mock
    private OrganizationService organizationService;
    @InjectMocks
    private OrganizationController controller;

    @Resource
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setup(){
        //MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }
*/