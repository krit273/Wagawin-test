package com.wagawin.test.controller;

import com.wagawin.test.entity.House;
import com.wagawin.test.repository.HouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/house")
@Slf4j
public class HouseController {
    private static final String ERROR_MESSAGE_404 = "House cannot be found by person ID = ";

    @Autowired
    private HouseRepository houseRepository;

    @GetMapping("/{id}")
    public House getPersonByHouseId(@PathVariable("id") Long personId) {
        Optional<House> houseOptional = houseRepository.findByPersonId(personId);
        if (houseOptional.isPresent()) {
            return houseOptional.get();
        } else {
            log.error(ERROR_MESSAGE_404 + personId);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ERROR_MESSAGE_404 + personId
            );
        }
    }
}
