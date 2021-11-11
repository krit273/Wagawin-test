package com.wagawin.test.repository;

import com.wagawin.test.entity.Child;
import com.wagawin.test.entity.House;
import com.wagawin.test.entity.HouseType;
import com.wagawin.test.entity.Person;
import com.wagawin.test.utils.TestUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.wagawin.test.utils.TestUtils.createPerson;

@SpringBootTest
@Transactional
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void savePerson() {
        Person person = createPerson();
        personRepository.save(person);

        Assert.assertNotNull(person.getId());
    }

    @Test
    public void savePersonWithHouse() {
        Person person = createPerson();
        House house = TestUtils.createHouse(HouseType.FLAT);
        person.setHouse(house);
        house.setPerson(person);

        personRepository.save(person);

        Assert.assertNotNull(person.getId());
    }

    @Test
    public void savePersonWithChildren() {
        Person person = createPerson();

        List<Child> children = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
            Child child = TestUtils.createChild();
            if (i % 2 == 0) {
                children.add(TestUtils.createSon(child));
            } else {
                children.add(TestUtils.createDaughter(child));
            }
        });
        person.setChildren(children);

        personRepository.save(person);

        Assert.assertNotNull(person.getId());
        for (Child child : children) {
            Assert.assertNotNull(child.getId());
        }
    }
}
