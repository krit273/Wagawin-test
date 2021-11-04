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
@DiscriminatorValue("1")
public class Son extends Child {
    private String bicycleColor;

    public Son(Long id, String name, Integer age, Person person, String bicycleColor, List<Meal> meals) {
        super(id, name, age, person, meals);
        this.bicycleColor = bicycleColor;
    }
}
