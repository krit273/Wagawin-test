package com.wagawin.test.repository;

import com.wagawin.test.entity.ParentSummary;
import com.wagawin.test.utils.TestUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ParentSummaryRepositoryTest {
    @Autowired
    private ParentSummaryRepository parentSummaryRepository;

    @Test
    public void saveParentSummary() {
        List<ParentSummary> parentSummaryList = TestUtils.createParentSummaryList();
        parentSummaryRepository.saveAll(parentSummaryList);
    }

    @Test
    public void findParentSummariesTest() {
        List<ParentSummary> parentSummaryList = TestUtils.createParentSummaryList();
        parentSummaryRepository.deleteAll();
        parentSummaryRepository.saveAll(parentSummaryList);
        List<ParentSummary> foundParentSummaryList = parentSummaryRepository.findAll();

        Assert.assertFalse(foundParentSummaryList.isEmpty());
    }
}
