package com.wagawin.test.repository;

import com.wagawin.test.entity.House;
import com.wagawin.test.entity.HouseType;
import com.wagawin.test.entity.Person;
import com.wagawin.test.utils.TestUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.wagawin.test.utils.TestUtils.createPerson;

@SpringBootTest
@Transactional
public class HouseRepositoryTest {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void saveHouse() {
        House house = TestUtils.createHouse(HouseType.ESTATE);
        Person person = TestUtils.createPerson();
        house.setPerson(person);
        houseRepository.save(house);

        Assert.assertNotNull(house.getId());
    }

    @Test
    public void findByPersonIdTest() {
        Person person = createPerson();
        House house = TestUtils.createHouse(HouseType.HOUSE);
        person.setHouse(house);
        house.setPerson(person);

        personRepository.save(person);
        Optional<House> optionalHouse = houseRepository.findByPersonId(person.getId());

        Assert.assertNotNull(house.getId());
        Assert.assertTrue(optionalHouse.isPresent());
    }
}
