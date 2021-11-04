package com.wagawin.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("2")
public class Daughter extends Child {
    private String hairColor;

    public Daughter(Long id, String name, Integer age, Person person, String hairColor, List<Meal> meals) {
        super(id, name, age, person, meals);
        this.hairColor = hairColor;
    }
}
