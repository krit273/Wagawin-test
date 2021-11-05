package com.wagawin.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties("person")
@Table(
        indexes = {
                @Index(columnList = "person_id", name = "person_idx")
        })
public class House implements Serializable {
    @Id
    private Long id;
    private String address;
    private String zipCode;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnoreProperties("house")
    private Person person;

    @Enumerated(EnumType.STRING)
    @Column(name = "house_type")
    private HouseType houseType;
}
