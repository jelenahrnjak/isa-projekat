package com.example.WishAndFish.service;
import com.example.WishAndFish.constants.BoatsConstants;
import com.example.WishAndFish.constants.CottageConstants;
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
        when(ruleRepositoryMock.findAll()).thenReturn(Arrays.asList(new Rule(RuleConstants.id2, "content", new Cottage(CottageConstants.id), null, false)));

        // 2. Akcija
        List<RuleDTO> rules = ruleService.getAllByCottage(CottageConstants.id);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertThat(rules).hasSize(1);
        assertTrue(rules.get(0).getId() == RuleConstants.id2);

		/*
		Možemo verifikovati ponašanje mokovanih objekata pozivanjem verify* metoda.
		 */
        verify(ruleRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(ruleRepositoryMock);
    }


    @Test
    @Transactional
    public void testRemove() {

        Rule rule = new Rule(RuleConstants.id3, RuleConstants.content, new Cottage(112L), null, false);
        Rule ruleToDelete  = new Rule(rule);
        ruleToDelete.setId(RuleConstants.id2);

        // 1. Definisanje ponašanja mock objekata
        when(ruleRepositoryMock.findAll()).thenReturn(Arrays.asList(rule, ruleToDelete));
        when(ruleRepositoryMock.save(rule)).thenReturn(rule);

        // 2. Akcija
        int dbSizeBeforeRemove = ruleService.getAllByCottage(CottageConstants.id).size();
        ruleService.deleteRule(RuleConstants.id2);

        when(ruleRepositoryMock.findAll()).thenReturn(Arrays.asList(rule));

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        List<RuleDTO> rules = ruleService.getAllByCottage(CottageConstants.id);
        assertThat(rules).hasSize(dbSizeBeforeRemove - 1);


        verify(ruleRepositoryMock, times(1)).save(ruleToDelete);
        verify(ruleRepositoryMock, times(3)).findAll();
        verifyNoMoreInteractions(ruleRepositoryMock);
    }


    @Test
    @Transactional
    public void testAddToBoat(){
        RuleDTO newRule = new RuleDTO();
        newRule.setContent(RuleConstants.content);
        newRule.setId(RuleConstants.id); //boat id

        Boat b  = new Boat();
        b.setId(BoatsConstants.id);
        Boat b2 = new Boat();
        b2.setId(BoatsConstants.id4);


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
