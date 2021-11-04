package com.wagawin.test.repository;

import com.wagawin.test.entity.Child;
import com.wagawin.test.entity.Daughter;
import com.wagawin.test.entity.Son;
import com.wagawin.test.utils.TestUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChildRepositoryTest {
    @Autowired
    private ChildRepository childRepository;

    @Test
    public void saveChild() {
        Child child = TestUtils.createChild();
        childRepository.save(child);

        Assert.assertNotNull(child.getId());
    }

    @Test
    public void saveSon() {
        Child child = TestUtils.createChild();
        Son son = TestUtils.createSon(child);
        childRepository.save(son);

        Assert.assertNotNull(son.getId());
    }

    @Test
    public void saveDaughter() {
        Child child = TestUtils.createChild();
        Daughter daughter = TestUtils.createDaughter(child);
        childRepository.save(daughter);

        Assert.assertNotNull(daughter.getId());
    }
}
