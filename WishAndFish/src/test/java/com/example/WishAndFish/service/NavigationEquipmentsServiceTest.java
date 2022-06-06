package com.example.WishAndFish.service;
import com.example.WishAndFish.constants.BoatsConstants;
import com.example.WishAndFish.constants.NavigationEquipmentsConstants;
import com.example.WishAndFish.constants.RuleConstants;
import com.example.WishAndFish.dto.NavigationEquipmentDTO;
import com.example.WishAndFish.dto.RuleDTO;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.NavigationEquipment;
import com.example.WishAndFish.model.Rule;
import com.example.WishAndFish.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NavigationEquipmentsServiceTest {

    @Mock
    private NavigationEquipmentRepository navigationEquipmentRepositoryMock;

    @Mock
    private BoatRepository boatRepositoryMock;


    @InjectMocks
    private NavigationEquipmentService navigationEquipmentService;

    @Test
    @Transactional
    public void testAdd(){
        NavigationEquipmentDTO newNE = new NavigationEquipmentDTO();
        newNE.setName(NavigationEquipmentsConstants.name);
        newNE.setId(1L);

        NavigationEquipment oldNE = new NavigationEquipment();
        oldNE.setName(NavigationEquipmentsConstants.name2);
        oldNE.setId(1L);

        NavigationEquipment n = new NavigationEquipment();
        n.setName(NavigationEquipmentsConstants.name);
        n.setDeleted(false);
        n.setId(NavigationEquipmentsConstants.id3);

        Boat b  = new Boat();
        b.setId(BoatsConstants.id);
        Boat b2 = new Boat();
        b2.setId(BoatsConstants.id4);


        when(boatRepositoryMock.findAll()).thenReturn(Arrays.asList(b,b2));

        NavigationEquipment added = new NavigationEquipment();
        added = navigationEquipmentService.addNavigationEquipment(newNE);
        assertThat(added).isNotNull();
        assertThat(added.getBoat()).isNotNull();

        verify(boatRepositoryMock, times(1)).findAll();
        verify(navigationEquipmentRepositoryMock, times(1)).save(added);

        verifyNoMoreInteractions(boatRepositoryMock);

    }

    @Test
    @Transactional
    public void testRemove() {
        Boat boat = new Boat();
        boat.setId(125L);
        NavigationEquipment nav = new NavigationEquipment(14L, NavigationEquipmentsConstants.name,false, boat);
        NavigationEquipment navToDelete  = new NavigationEquipment(nav);
        navToDelete.setId(NavigationEquipmentsConstants.id2);

        // 1. Definisanje ponašanja mock objekata
        when(navigationEquipmentRepositoryMock.findAll()).thenReturn(Arrays.asList(nav, navToDelete));
        //when(navigationEquipmentRepositoryMock.save(nav)).thenReturn(nav);

        // 2. Akcija
        int dbSizeBeforeRemove = navigationEquipmentService.getAllByBoat(BoatsConstants.id6).size();
        navigationEquipmentService.deleteNavigationEquipment(NavigationEquipmentsConstants.id2);

        when(navigationEquipmentRepositoryMock.findAll()).thenReturn(List.of(nav));

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        List<NavigationEquipmentDTO> rules = navigationEquipmentService.getAllByBoat(BoatsConstants.id6);
        assertThat(rules).hasSize(dbSizeBeforeRemove - 1);

        verify(navigationEquipmentRepositoryMock, times(1)).save(navToDelete);
        verify(navigationEquipmentRepositoryMock, times(3)).findAll();
        verifyNoMoreInteractions(navigationEquipmentRepositoryMock);
    }
}
