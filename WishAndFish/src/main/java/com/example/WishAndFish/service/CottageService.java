package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.CottageDTO;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CottageService {

    @Autowired
    private CottageRepository cottageRepository;

    public List<CottageDTO> findAll() {

        List<CottageDTO> ret = new ArrayList<CottageDTO>();
        for(Cottage c : cottageRepository.findAll((Sort.by(Sort.Direction.ASC, "name")))){
            ret.add(new CottageDTO(c));
        };

        return ret;
    }
}
