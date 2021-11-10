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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


@RestController
@RequestMapping("/persons")
public class PersonsController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private static final String ERROR_MESSAGE_404 = "ParentSummaries are not found";

    @Autowired
    private ParentSummaryRepository personRepository;

    @GetMapping("/children")
    public Map<Long, Long> getParentSummaryList() {
        List<ParentSummary> parentSummaryList = personRepository.findAll();
        if (!parentSummaryList.isEmpty()) {
            return getFilledAndOrderedParentSummaryMap(parentSummaryList);
        } else {
            LOG.error(ERROR_MESSAGE_404);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ERROR_MESSAGE_404
            );
        }
    }

    private Map<Long, Long> getFilledAndOrderedParentSummaryMap(List<ParentSummary> parentSummaryList) {
        Map<Long, Long> parentSummaryMap = parentSummaryList
                .stream().collect(Collectors.toMap(ParentSummary::getAmountOfChildren, ParentSummary::getAmountOfPersons, (a, b) -> b));

        Long maxAmountOfChildren = Collections.max(parentSummaryMap.entrySet(), Map.Entry.comparingByKey()).getKey();

        LongStream.range(0, maxAmountOfChildren).forEach(i -> {
            if (!parentSummaryMap.containsKey(i)) {
                parentSummaryMap.put(i, 0L);
            }
        });
        return parentSummaryMap
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey()).
                        collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
