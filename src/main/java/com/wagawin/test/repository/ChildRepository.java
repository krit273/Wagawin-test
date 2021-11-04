package com.wagawin.test.repository;

import com.wagawin.test.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
}
