package com.example.WishAndFish.controller;
import com.example.WishAndFish.constants.BoatsConstants;
import com.example.WishAndFish.dto.AddBoatDTO;
import com.example.WishAndFish.dto.AddressDTO;
import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.dto.EditBoatDTO;
import com.example.WishAndFish.model.Address;
import com.example.WishAndFish.model.Boat;
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
public class BoatControllerTest {

    private static final String URL_PREFIX = "/api/boats";

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
    public void testGetBoatById() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/findBoat/" + BoatsConstants.id)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(BoatsConstants.id.intValue()))
                .andExpect(jsonPath("$.name").value("Yacht"))
                .andExpect(jsonPath("$.description").value("Have great time on our boat"));
    }

    @Test
    public void testGetNumberOfBoats() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @Transactional
    @WithMockUser(authorities = "ROLE_BOAT_OWNER")
    public void testSaveBoat() throws Exception {

        AddBoatDTO boat = new AddBoatDTO();
        boat.setName(BoatsConstants.name);
        boat.setType(BoatsConstants.type);
        boat.setLength(BoatsConstants.length);
        boat.setEngineNumber(BoatsConstants.engineNumber);
        boat.setEnginePower(BoatsConstants.enginePower);
        boat.setMaxSpeed(BoatsConstants.length);
        boat.setAddress(new Address("ulica","123",
                "21000","grad","drzava",
                62.12,12.12));
        boat.setDescription(BoatsConstants.description);
        boat.setCapacity(BoatsConstants.capacity);
        boat.setPricePerDay(BoatsConstants.length);
        boat.setMaxSpeed(BoatsConstants.maxSpeed);
        boat.setOwnerEmail(BoatsConstants.ownerEmail);
        boat.setCancellationConditions(BoatsConstants.cancellationConditions);
        boat.setCoverImage(BoatsConstants.image);

        // kreiran brod saljemo u kontroler
        String json = TestUtil.json(boat);
        String newURL = URL_PREFIX + "/addBoat";
        this.mockMvc.perform(post(newURL).contentType(contentType).content(json)).andExpect(status().isOk());
    }



    @Test
    @Transactional
    @WithMockUser(authorities = "ROLE_BOAT_OWNER")
    public void testUpdateBoat() throws Exception {

        EditBoatDTO boat = new EditBoatDTO();
        boat.setId(125L);
        boat.setName(BoatsConstants.name);
        boat.setType(BoatsConstants.type);
        boat.setLength(BoatsConstants.length);
        boat.setEngineNumber(BoatsConstants.engineNumber);
        boat.setEnginePower(BoatsConstants.enginePower);
        boat.setMaxSpeed(BoatsConstants.length);
        boat.setAddress(new AddressDTO("ulica","123",
                "21000", 15, 16, "grad","drzava"));
        boat.setDescription(BoatsConstants.description);
        boat.setCapacity(BoatsConstants.capacity);
        boat.setCancellationConditions(BoatsConstants.cancellationConditions);
        boat.setPricePerHour(BoatsConstants.pricePerDay);

        String json = TestUtil.json(boat);
        String newURL = URL_PREFIX + "/editBasicInfo";
        this.mockMvc.perform(put(newURL).contentType(contentType).content(json)).andExpect(status().isOk());
    }

}
