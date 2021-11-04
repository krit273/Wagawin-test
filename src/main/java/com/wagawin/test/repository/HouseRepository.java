package com.wagawin.test.repository;

import com.wagawin.test.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    Optional<House> findByPersonId(Long id);
}
