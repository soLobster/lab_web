package com.itwill.springboot3.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "LOCATIONS")
public class Location {

    @Id
    @Column(name = "LOCATION_ID")
    private Integer id;
    
    @Column(name = "STREET_ADDRESS")
    private String address;
    
    private String postal_code;
    
    private String city;
    
    @Column(name = "STATE_PROVINCE")
    private String state;
    
    @Column(name = "COUNTRY_ID")
    private String country;
}
