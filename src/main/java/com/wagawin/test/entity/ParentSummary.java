package com.wagawin.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentSummary {
    @Id
    private Long amountOfChildren;
    private Long amountOfPersons;
}
