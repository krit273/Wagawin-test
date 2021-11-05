package com.wagawin.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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
public class Child implements Serializable {
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "child_id")
    private List<Meal> meals;
}
