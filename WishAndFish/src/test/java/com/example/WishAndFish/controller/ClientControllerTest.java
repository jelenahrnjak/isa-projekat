package com.example.WishAndFish.controller;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testGetCottageSubscriptions() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/cottageSubscriptions/" + ClientConstants.DB_EMAIL)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testGetBoatSubscriptions() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/boatSubscriptions/" + ClientConstants.DB_EMAIL)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
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


        this.mockMvc.perform(put(newURL).contentType(contentType).content(json)).andExpect(status().isOk())
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

        this.mockMvc.perform(put(newURL).contentType(contentType).content(json)).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testGetPenalties() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getPenalties/" + ClientConstants.DB_EMAIL)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$").value(ClientConstants.DB_NUM_OF_PENALITES));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testAddPenaltyToClient() throws Exception {

        String json = TestUtil.json(ClientConstants.DB_EMAIL);
        String newURL = URL_PREFIX + "/addPenaltyToClient";
        this.mockMvc.perform(put(newURL).contentType(contentType).content(ClientConstants.DB_EMAIL)).andExpect(status().isOk());

    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testUnsubscribeFromCottage() throws Exception {

        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setCottageId(CottageConstants.id5);
        dto.setUserEmail(ClientConstants.DB_EMAIL);

        String json = TestUtil.json(dto);
        String newURL = URL_PREFIX + "/unsubscribeFromCottage";

        this.mockMvc.perform(put(newURL).contentType(contentType).content(json)).andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Successfully subscribed"));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testUnsubscribeFromCottageThatDoesNotExists() throws Exception {

        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setCottageId(CottageConstants.id4);
        dto.setUserEmail(ClientConstants.DB_EMAIL);

        String json = TestUtil.json(dto);
        String newURL = URL_PREFIX + "/unsubscribeFromCottage";

        this.mockMvc.perform(put(newURL).contentType(contentType).content(json)).andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testSubscribeToBoat() throws Exception {

        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setBoatId(BoatsConstants.id6);
        dto.setUserEmail(ClientConstants.DB_EMAIL);

        String json = TestUtil.json(dto);
        String newURL = URL_PREFIX + "/subscribeToBoat";


        this.mockMvc.perform(put(newURL).contentType(contentType).content(json)).andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Successfully subscribed"));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testUnsubscribeToBoat() throws Exception {

        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setBoatId(BoatsConstants.id7);
        dto.setUserEmail(ClientConstants.DB_EMAIL);

        String json = TestUtil.json(dto);
        String newURL = URL_PREFIX + "/unsubscribeFromBoat";


        this.mockMvc.perform(put(newURL).contentType(contentType).content(json)).andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Successfully subscribed"));
    }
}
