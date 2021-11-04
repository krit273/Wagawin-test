package com.wagawin.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        indexes = {
                @Index(columnList = "child_id", name = "child_idx")
        })
public class Meal {
    @Id
    @SequenceGenerator(
            name = "meal_sequence",
            sequenceName = "meal_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "meal_sequence"
    )
    private Long id;
    private String name;

    @Column
    @Type(type="timestamp")
    private Date invented;
}
