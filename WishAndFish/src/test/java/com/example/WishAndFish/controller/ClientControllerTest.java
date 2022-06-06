package com.example.WishAndFish.controller;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.WishAndFish.constants.ClientConstants;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientControllerTest {

    private static final String URL_PREFIX = "/api/clients";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testGetcottageSubscriptions() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/cottageSubscriptions/" + ClientConstants.DB_EMAIL)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testGetBoatSubscriptions() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/boatSubscriptions/" + ClientConstants.DB_EMAIL)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testGetAdventureSubscriptions() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/adventureSubscriptions/" + ClientConstants.DB_EMAIL)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)));
    }

}
