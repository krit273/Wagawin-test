package com.wagawin.test.repository;

import com.wagawin.test.entity.ParentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ParentSummaryRepository extends JpaRepository<ParentSummary, Long> {
    @Query(value = "select amount_of_children , COUNT(pcC.pid) as amount_of_persons\n" +
            "from (select c.person_id as pid, COUNT(*) as amount_of_children\n" +
            "      from child c group by c.person_id) as pcC group by pcC.amount_of_children" +
            " ORDER BY pcC.amount_of_children;", nativeQuery = true)
    List<ParentSummary> findParentSummaries();
}
