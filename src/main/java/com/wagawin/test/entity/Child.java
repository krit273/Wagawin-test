package com.wagawin.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "child_type",
        discriminatorType = DiscriminatorType.INTEGER)
@Table(
        indexes = {
                @Index(columnList = "person_id", name = "person_idx")
        })
public class Child {
    @Id
    @SequenceGenerator(
            name = "child_sequence",
            sequenceName = "child_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "child_sequence"
    )
    private Long id;
    private String name;
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "child_id")
    private List<Meal> meals;
}
