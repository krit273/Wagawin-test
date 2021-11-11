package com.wagawin.test.repository;

import com.wagawin.test.entity.ParentSummary;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ParentSummaryRepositoryTest {
    @Autowired
    private ParentSummaryRepository parentSummaryRepository;

    @Test
    public void findAndSaveParentSummary() {
        List<ParentSummary> parentSummaryList = parentSummaryRepository.findParentSummaries();
        parentSummaryRepository.deleteAll();
        parentSummaryRepository.saveAll(parentSummaryList);
        List<ParentSummary> foundParentSummaryList = parentSummaryRepository.findAll();

        Assert.assertFalse(foundParentSummaryList.isEmpty());
    }
}
