package ru.bellintegrator.practice.organization.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@EnableWebMvc
@DirtiesContext
public class OrganizationControllerTest {
    @Autowired
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

    @Test
    public void metod() throws Exception{
        OrganizationView organization = new OrganizationView();
        when(organizationService.getOrganization("1")).thenReturn(organization);

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
    public void metodAll () throws Exception {
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