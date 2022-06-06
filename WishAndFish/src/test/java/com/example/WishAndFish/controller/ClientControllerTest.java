package com.example.WishAndFish.controller;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.WishAndFish.constants.AddressConstants;
import com.example.WishAndFish.constants.BoatsConstants;
import com.example.WishAndFish.constants.ClientConstants;
import com.example.WishAndFish.constants.CottageConstants;
import com.example.WishAndFish.dto.AddCottageDTO;
import com.example.WishAndFish.dto.AddressDTO;
import com.example.WishAndFish.dto.SubscriptionDTO;
import com.example.WishAndFish.model.Address;
import com.example.WishAndFish.util.TestUtil;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testGetcottageSubscriptions() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/cottageSubscriptions/" + ClientConstants.DB_EMAIL)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @Transactional
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testGetBoatSubscriptions() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/boatSubscriptions/" + ClientConstants.DB_EMAIL)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Transactional
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testGetAdventureSubscriptions() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/adventureSubscriptions/" + ClientConstants.DB_EMAIL)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testSubscribeToCottage() throws Exception {

        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setCottageId(CottageConstants.id);
        dto.setUserEmail(ClientConstants.DB_EMAIL);

        String json = TestUtil.json(dto);
        String newURL = URL_PREFIX + "/subscribeToCottage";

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(newURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json);

        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(jsonPath("$.text").value("Successfully subscribed"));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testSubscribeToExistingCottageInSubscriptions() throws Exception {

        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setCottageId(CottageConstants.id5);
        dto.setUserEmail(ClientConstants.DB_EMAIL);

        String json = TestUtil.json(dto);
        String newURL = URL_PREFIX + "/subscribeToCottage";

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(newURL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json);

        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isBadRequest());
    }
}
