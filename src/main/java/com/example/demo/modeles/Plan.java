package com.example.demo.modeles;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.soap.SAAJResult;

@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private String nameOfPlan;

    public Plan() {
    }

    public Plan(String text, String nameOfPlan) {
        this.text = text;
        this.nameOfPlan = nameOfPlan;
    }

    public String getNameOfPlan() {
        return nameOfPlan;
    }

    public void setNameOfPlan(String nameOfPlan) {
        this.nameOfPlan = nameOfPlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
