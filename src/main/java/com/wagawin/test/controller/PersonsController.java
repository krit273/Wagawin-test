package com.wagawin.test.controller;

import com.wagawin.test.entity.ParentSummary;
import com.wagawin.test.repository.ParentSummaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonsController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private static final String ERROR_MESSAGE_404 = "ParentSummaries are not found";

    @Autowired
    private ParentSummaryRepository personRepository;

    @GetMapping("/children")
    public List<ParentSummary> getParentSummaryList() {
        List<ParentSummary> parentSummaryList = personRepository.findParentSummaries();
        if (!parentSummaryList.isEmpty()) {
            return personRepository.findParentSummaries();
        } else {
            LOG.error(ERROR_MESSAGE_404);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ERROR_MESSAGE_404
            );
        }
    }
}
