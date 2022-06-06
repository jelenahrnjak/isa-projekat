package com.example.WishAndFish.controller;
import com.example.WishAndFish.constants.AddressConstants;
import com.example.WishAndFish.constants.BoatsConstants;
import com.example.WishAndFish.constants.CottageConstants;
import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.Address;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CottageControllerTest {
    private static final String URL_PREFIX = "/api/cottages";

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
    public void testGetCottageById() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/findCottage/" + CottageConstants.id4)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(CottageConstants.id4))
                .andExpect(jsonPath("$.name").value("The Overlook"))
                .andExpect(jsonPath("$.description").value("Cosy cottage to enjoy with family and friends"));
    }

    @Test
    public void testGetNumberOfCottages() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(4)));
    }


    @Test
    @Transactional
    @WithMockUser(authorities = "ROLE_COTTAGE_OWNER")
    public void testSaveCottage() throws Exception {

        AddCottageDTO cottage = new AddCottageDTO();
        cottage.setName(BoatsConstants.name);
        cottage.setDescription("super");
        cottage.setAddress(new AddressDTO(new Address (AddressConstants.street,AddressConstants.streetNumber,
                AddressConstants.postalCode,AddressConstants.city,AddressConstants.country,
                AddressConstants.lng,AddressConstants.ltd)));
        cottage.setPrice(CottageConstants.price);
        cottage.setOwnerEmail(CottageConstants.email);
        cottage.setCoverImage(CottageConstants.image);

        cottage.setNumberOfRooms(CottageConstants.rooms);
        cottage.setBedsPerRoom(CottageConstants.beds);


        // kreiran brod saljemo u kontroler
        String json = TestUtil.json(cottage);
        String newURL = URL_PREFIX + "/addCottage";
        this.mockMvc.perform(post(newURL).contentType(contentType).content(json)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(BoatsConstants.name));

    }



    @Test
    @Transactional
    @WithMockUser(authorities = "ROLE_COTTAGE_OWNER")
    public void testUpdateCottage() throws Exception {

        EditCottageDTO cottage = new EditCottageDTO();
        cottage.setId(CottageConstants.id4);
        cottage.setName(CottageConstants.name3);
        cottage.setDescription(CottageConstants.description);
        cottage.setAddress(new AddressDTO(new Address (AddressConstants.street,AddressConstants.streetNumber,
                AddressConstants.postalCode,AddressConstants.city,AddressConstants.country,
                AddressConstants.lng,AddressConstants.ltd)));
        cottage.setPricePerDay(CottageConstants.price);
        cottage.setBedsPerRoom(CottageConstants.beds);

        String json = TestUtil.json(cottage);
        String newURL = URL_PREFIX + "/editBasicInfo";
        this.mockMvc.perform(put(newURL).contentType(contentType).content(json)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(CottageConstants.name3));

    }



    @Test
    @Transactional
    @WithMockUser(authorities = "ROLE_COTTAGE_OWNER")
    public void testDeleteBoat() throws Exception {
        this.mockMvc.perform(delete(URL_PREFIX + "/deleteCottage/" + CottageConstants.id4)).andExpect(status().isOk())
                .andExpect(jsonPath("$").value(CottageConstants.id4));

    }

}
