package com.wagawin.test.controller;

import com.wagawin.test.entity.Child;
import com.wagawin.test.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String ERROR_MESSAGE_404 = "Child with ID = %d is not found";

    @Autowired
    private ChildRepository childRepository;

    @GetMapping("/info/{id}")
    public Child getChildById(@PathVariable("id") Long childId) {
        Optional<Child> childOptional = childRepository.findById(childId);
        if (childOptional.isPresent()) {
            return childOptional.get();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format(ERROR_MESSAGE_404, childId)
            );
        }
    }
}
