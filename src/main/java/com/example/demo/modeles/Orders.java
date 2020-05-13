package com.example.demo.modeles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String  full_order, country, firstName, lastName, zip, adress;



    public Orders( String firstName, String lastName, String full_order, String country, String adress, String zip) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.full_order = full_order;
        this.country = country;
        this.adress = adress;
        this.zip = zip;

    }
    public Orders() {
    }
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }




    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getFull_order() {
        return full_order;
    }

    public void setFull_order(String full_order) {
        this.full_order = full_order;
    }


}
