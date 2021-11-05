package com.wagawin.test.populate;

import com.wagawin.test.entity.Child;
import com.wagawin.test.entity.House;
import com.wagawin.test.entity.HouseType;
import com.wagawin.test.entity.Person;
import com.wagawin.test.repository.PersonRepository;
import com.wagawin.test.utils.TestUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static com.wagawin.test.utils.TestUtils.createPerson;

@SpringBootTest()
public class PopulateTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private static Random rand = new Random();

    @Autowired
    private PersonRepository personRepository;


    @Test
    public void savePersonWithChildrenAndHouseTest() {
        // Uncomment next line to populate your db
//        savePeopleWithChildrenAndHouseTest(10000);
    }

    private void savePeopleWithChildrenAndHouseTest(int amountOfPeople) {
        for (int j = 0; j < amountOfPeople; j++) {
            if (j % 100 == 0) {
                LOG.info("The current amount of inserted people is " + j);
            }
            savePersonWithChildrenAndHouse();
        }
    }

    private void savePersonWithChildrenAndHouse() {
        Person person = createPerson();

        House house = TestUtils.createHouse(HouseType.FLAT);
        person.setHouse(house);
        house.setPerson(person);

        List<Child> children = new ArrayList<>();
        IntStream.range(0, rand.nextInt(5)).forEach(i -> {
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
