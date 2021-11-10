package com.wagawin.test.controller;

import com.wagawin.test.entity.Child;
import com.wagawin.test.entity.Daughter;
import com.wagawin.test.entity.Son;
import com.wagawin.test.repository.ChildRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/color")
public class ColorController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private static final String CHILD_TYPE_IS_NOT_DEFINED_ERROR = "Type of the Child with ID = %d is not defined";
    private static final String ERROR_MESSAGE_404 = "Child with ID = %d is not found";

    @Autowired
    private ChildRepository childRepository;

    @GetMapping("/{id}")
    public Map<String, Object> getColor(@PathVariable("id") Long childId) {
        Optional<Child> childOptional = childRepository.findById(childId);

        if (childOptional.isPresent()) {
            Child child = childOptional.get();
            Map<String, Object> map = new HashMap<>();
            if (child instanceof Son) {
                map.put("bicycleColor", ((Son) child).getBicycleColor());
            } else if (child instanceof Daughter) {
                map.put("hairColor", ((Daughter) child).getHairColor());
            } else {
                LOG.error(String.format(CHILD_TYPE_IS_NOT_DEFINED_ERROR, childId));
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, String.format(CHILD_TYPE_IS_NOT_DEFINED_ERROR, childId)
                );
            }
            return map;
        } else {
            LOG.error(String.format(ERROR_MESSAGE_404, childId));
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format(ERROR_MESSAGE_404, childId)
            );
        }
    }
}
