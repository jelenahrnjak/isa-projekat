package com.example.WishAndFish.service;
import com.example.WishAndFish.constants.BoatsConstants;
import com.example.WishAndFish.constants.RuleConstants;
import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.BoatRepository;
import com.example.WishAndFish.repository.ReservationRepository;
import com.example.WishAndFish.repository.RuleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleServiceTest {

    @Mock
    private RuleRepository ruleRepositoryMock;

    @Mock
    private BoatRepository boatRepositoryMock;

    @Mock
    private Rule ruleMock;

    @InjectMocks
    private RuleService ruleService;



    @Test
    public void testGetAll() {
        // 1. Definisanje ponašanja mock objekata (ja mu kazem kada se pozove metoda mock-a, da mi vrati tu konkretnu kucu (definisem ponasanje metode))
        when(ruleRepositoryMock.findAll()).thenReturn(Arrays.asList(new Rule(15l, "content", new Cottage(112L), null, false)));

        // 2. Akcija
        List<RuleDTO> rules = ruleService.getAllByCottage(112L);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertThat(rules).hasSize(1);
        assertTrue(rules.get(0).getId() == 15L);

		/*
		Možemo verifikovati ponašanje mokovanih objekata pozivanjem verify* metoda.
		 */
        verify(ruleRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(ruleRepositoryMock);
    }


    @Test
    @Transactional
    public void testRemove() {

        Rule rule = new Rule(26L, RuleConstants.DB_CONTENT, new Cottage(112L), null, false);
        Rule ruleToDelete  = new Rule(rule);
        ruleToDelete.setId(15L);

        // 1. Definisanje ponašanja mock objekata
        when(ruleRepositoryMock.findAll()).thenReturn(Arrays.asList(rule, ruleToDelete));
        when(ruleRepositoryMock.save(rule)).thenReturn(rule);

        // 2. Akcija
        int dbSizeBeforeRemove = ruleService.getAllByCottage(112L).size();
        ruleService.deleteRule(15L);

        when(ruleRepositoryMock.findAll()).thenReturn(Arrays.asList(rule));

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        List<RuleDTO> rules = ruleService.getAllByCottage(112l);
        assertThat(rules).hasSize(dbSizeBeforeRemove - 1);


        verify(ruleRepositoryMock, times(1)).save(ruleToDelete);
        verify(ruleRepositoryMock, times(3)).findAll();
        verifyNoMoreInteractions(ruleRepositoryMock);
    }


    @Test
    @Transactional
    public void testAddToBoat(){
        RuleDTO newRule = new RuleDTO();
        newRule.setContent("No smoking");
        newRule.setId(1L); //boat id

        Boat b  = new Boat();
        b.setId(1L);
        Boat b2 = new Boat();
        b2.setId(2L);


        when(boatRepositoryMock.findAll()).thenReturn(Arrays.asList(b,b2));

        Rule added = new Rule();
        added = ruleService.addRuleBoat(newRule);
        assertThat(added).isNotNull();
        assertThat(added.getBoat()).isNotNull();
        assertThat(added.getBoat().getId()).isEqualTo(newRule.getId());

        verify(boatRepositoryMock, times(1)).findAll();
        verify(ruleRepositoryMock, times(1)).save(added);

        verifyNoMoreInteractions(ruleRepositoryMock);

    }
}
