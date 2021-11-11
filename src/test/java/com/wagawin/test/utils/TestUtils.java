package com.wagawin.test.utils;

import com.github.javafaker.Faker;
import com.wagawin.test.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class TestUtils {
    private static final int GLOBAL_AVERAGE_LIFE_EXPECTANCY = 73;
    private static final int CHILDREN_MAX_AGE = 18;

    private static final Random rand = new Random();
    private static final Faker faker = new Faker();

    public static Person createPerson() {
        return Person.builder()
                .name(faker.name().fullName())
                .age(rand.nextInt(GLOBAL_AVERAGE_LIFE_EXPECTANCY))
                .build();
    }

    public static Child createChild() {
        return Child.builder()
                .age(rand.nextInt(CHILDREN_MAX_AGE))
                .name(faker.name().fullName())
                .meals(createMeals(2))
                .build();
    }

    public static Daughter createDaughter(Child child) {
        return new Daughter(child.getId(), child.getName(),
                child.getAge(), child.getPerson(), faker.color().name(), child.getMeals());
    }

    public static Son createSon(Child child) {
        return new Son(child.getId(), child.getName(),
                child.getAge(), child.getPerson(), faker.color().name(), child.getMeals());
    }

    public static List<Meal> createMeals(int amountOfMeals) {
        List<Meal> meals = new ArrayList<>();
        IntStream.range(0, amountOfMeals).forEach(i ->
                meals.add(Meal.builder().name(faker.food().dish()).invented(faker.date().birthday()).build()));
        return meals;
    }

    public static House createHouse(HouseType houseType) {
        return House.builder().houseType(houseType)
                .address(faker.address().fullAddress())
                .zipCode(faker.address().zipCode()).build();
    }

    public static List<ParentSummary> createParentSummaryList() {
        List<ParentSummary> parentSummaryList = new ArrayList<>();
        LongStream.range(0, 10).forEach(i -> parentSummaryList.add(new ParentSummary(i, (long) rand.nextInt(100))));
        return parentSummaryList;
    }
}
