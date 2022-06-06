package com.example.WishAndFish.service;

import com.example.WishAndFish.constants.ClientConstants;
import com.example.WishAndFish.dto.FishingAdventureDTO;
import com.example.WishAndFish.model.Address;
import com.example.WishAndFish.model.FishingAdventure;
import com.example.WishAndFish.model.FishingInstructor;
import com.example.WishAndFish.repository.FishingAdventureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.example.WishAndFish.constants.AddressConstants.*;
import static com.example.WishAndFish.constants.FishingAdventureConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventureServiceTest {

    @Mock
    FishingAdventureRepository fishingAdventureRepositoryMock;

    @Mock
    FishingAdventure fishingAdventureMock;

    @InjectMocks
    FishingAdventuresService fishingAdventuresService;

    @Test
    public void testSearch() {

        FishingAdventure adventure1 = new FishingAdventure(DB_ID, DB_NAME, DB_RATING1, DB_PRICE);
        FishingAdventure adventure2 = new FishingAdventure(DB_ID2, DB_NAME2, DB_RATING1, DB_PRICE);
        FishingAdventure adventure3 = new FishingAdventure(DB_ID3, DB_NAME3, DB_RATING2, DB_PRICE);
        FishingInstructor instructor = new FishingInstructor(ClientConstants.DB_PASS, ClientConstants.DB_EMAIL, ClientConstants.DB_NAME, ClientConstants.DB_SURNAME, ClientConstants.DB_PHONE);
        Address address = new Address(DB_STREET, DB_NUMBER, DB_POSTAL, DB_CITY, DB_COUNTRY);
        address.setId(DB_ADDRESS_ID);

        adventure1.setFishingInstructor(instructor);
        adventure2.setFishingInstructor(instructor);
        adventure3.setFishingInstructor(instructor);
        adventure1.setAddress(address);
        adventure2.setAddress(address);
        adventure3.setAddress(address);

        when(fishingAdventureRepositoryMock.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))).thenReturn(Arrays.asList(adventure1,adventure2,adventure3));

        FishingAdventureDTO dto = new FishingAdventureDTO("adventure", "", "3", "100");

        List<FishingAdventure> adventures = fishingAdventuresService.search(dto);

        assertThat(adventures).hasSize(2);
        assertTrue(adventures.contains(adventure1));

        verify(fishingAdventureRepositoryMock, times(1)).findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")));
        verifyNoMoreInteractions(fishingAdventureRepositoryMock);
    }

    @Test
    public void testSearchByInstructor() {

        FishingAdventure adventure1 = new FishingAdventure(DB_ID, DB_NAME, DB_RATING1, DB_PRICE);
        FishingAdventure adventure2 = new FishingAdventure(DB_ID2, DB_NAME2, DB_RATING1, DB_PRICE);
        FishingAdventure adventure3 = new FishingAdventure(DB_ID3, DB_NAME3, DB_RATING2, DB_PRICE);
        FishingInstructor instructor = new FishingInstructor(ClientConstants.DB_PASS, ClientConstants.DB_EMAIL, ClientConstants.DB_NAME, ClientConstants.DB_SURNAME, ClientConstants.DB_PHONE);
        Address address = new Address(DB_STREET, DB_NUMBER, DB_POSTAL, DB_CITY, DB_COUNTRY);
        address.setId(DB_ADDRESS_ID);
        FishingInstructor instructor2 = new FishingInstructor(instructor);
        instructor2.setName(ClientConstants.DB_NAME2);
        instructor2.setSurname(ClientConstants.DB_SURNAME2);

        adventure1.setFishingInstructor(instructor);
        adventure2.setFishingInstructor(instructor2);
        adventure3.setFishingInstructor(instructor);
        adventure1.setAddress(address);
        adventure2.setAddress(address);
        adventure3.setAddress(address);

        when(fishingAdventureRepositoryMock.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))).thenReturn(Arrays.asList(adventure1,adventure2,adventure3));

        FishingAdventureDTO dto = new FishingAdventureDTO("adventure", "", "3", "100");
        dto.setInstructor(ClientConstants.DB_NAME2 + " " + ClientConstants.DB_SURNAME2);

        List<FishingAdventure> adventures = fishingAdventuresService.search(dto);

        assertThat(adventures).hasSize(1);
        assertEquals(adventures.get(0).getName(), DB_NAME2);

        verify(fishingAdventureRepositoryMock, times(1)).findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")));
        verifyNoMoreInteractions(fishingAdventureRepositoryMock);
    }
}
