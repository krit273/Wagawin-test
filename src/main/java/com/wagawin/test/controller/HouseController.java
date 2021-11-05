package com.wagawin.test.controller;

import com.wagawin.test.entity.House;
import com.wagawin.test.repository.HouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/house")
public class HouseController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private static final String ERROR_MESSAGE_404 = "House cannot be found by person ID = ";

    @Autowired
    private HouseRepository houseRepository;

    @GetMapping("/{id}")
    @Cacheable(value = "houses", key = "#personId")
    public House getPersonByHouseId(@PathVariable("id") Long personId) {
        LOG.info("Getting house by personId {}.", personId);
        System.out.println("Getting house by personId " + personId);
        Optional<House> houseOptional = houseRepository.findByPersonId(personId);
        if (houseOptional.isPresent()) {
            return houseOptional.get();
        } else {
            LOG.error(ERROR_MESSAGE_404 + personId);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ERROR_MESSAGE_404 + personId
            );
        }
    }
}
