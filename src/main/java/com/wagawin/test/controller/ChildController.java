package com.wagawin.test.controller;

import com.wagawin.test.entity.Child;
import com.wagawin.test.repository.ChildRepository;
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
@RequestMapping("/child")
public class ChildController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private static final String ERROR_MESSAGE_404 = "Child with ID = %d is not found";

    @Autowired
    private ChildRepository childRepository;

    @GetMapping("/info/{id}")
    @Cacheable(value = "child", key = "#childId")
    public Child getChildById(@PathVariable("id") Long childId) {
        LOG.info("Getting child by id {}.", childId);
        Optional<Child> childOptional = childRepository.findById(childId);
        if (childOptional.isPresent()) {
            return childOptional.get();
        } else {
            LOG.error(String.format(ERROR_MESSAGE_404, childId));
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format(ERROR_MESSAGE_404, childId)
            );
        }
    }
}
