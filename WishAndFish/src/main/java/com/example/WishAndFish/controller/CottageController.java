package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.CottageDTO;
import com.example.WishAndFish.service.CottageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/cottages")
@CrossOrigin()
public class CottageController {

    @Autowired
    private CottageService cottageService;

    private Logger logger = LoggerFactory.getLogger(CottageController.class);

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<CottageDTO> getAll() {
        return this.cottageService.findAll();
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public List<CottageDTO> search(CottageDTO dto) {
        return this.cottageService.search(dto);
    }

}
