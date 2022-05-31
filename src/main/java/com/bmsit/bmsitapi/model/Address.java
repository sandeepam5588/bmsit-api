package com.bmsit.bmsitapi.model;

import lombok.Data;

import javax.persistence.*;

@Data
//@Embeddable
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String addressLine1;
    private String addressLine2;
    private String street;
    private String city;
    private String state;
    private String country;
    private int zipCode;
}
