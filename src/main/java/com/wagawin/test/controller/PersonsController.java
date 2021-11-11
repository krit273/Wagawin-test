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
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


@RestController
@RequestMapping("/persons")
public class PersonsController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private static final String ERROR_MESSAGE_404 = "ParentSummaries are not found";

    @Autowired
    private ParentSummaryRepository parentSummaryRepository;

    @GetMapping("/children")
    public Map<Long, Long> getParentSummaryList() {
        List<ParentSummary> parentSummaryList = parentSummaryRepository.findAll();
        if (!parentSummaryList.isEmpty()) {
            return getFilledAndOrderedParentSummary(parentSummaryList);
        } else {
            LOG.error(ERROR_MESSAGE_404);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ERROR_MESSAGE_404
            );
        }
    }

    private Map<Long, Long> getFilledAndOrderedParentSummary(List<ParentSummary> parentSummaryList) {
        Long maxAmountOfChildren = parentSummaryList.stream()
                .reduce((first, second) -> second).map(ParentSummary::getAmountOfChildren).get();

        Map<Long, Long> parentSummaryMap = parentSummaryList
                .stream().collect(Collectors
                        .toMap(ParentSummary::getAmountOfChildren, ParentSummary::getAmountOfPersons,
                                (a, b) -> b, TreeMap::new));

        LongStream.range(0, maxAmountOfChildren).forEach(i -> parentSummaryMap.putIfAbsent(i, 0L));

        return parentSummaryMap;
    }
}