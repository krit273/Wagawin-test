package com.wagawin.test.scheduler;

import com.wagawin.test.entity.ParentSummary;
import com.wagawin.test.repository.ParentSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParentSummaryScheduler {
    @Autowired
    ParentSummaryRepository parentSummaryRepository;

    //At every 15th minute
    @Scheduled(cron = "0 0/15 * * * ?")
    public void scheduleTaskWithFixedRate() {
        List<ParentSummary> parentSummaryList = parentSummaryRepository.findParentSummaries();
        parentSummaryRepository.saveAll(parentSummaryList);
    }
}
