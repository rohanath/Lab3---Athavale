package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String work1;

    private String work2;

    private String ed1;

    private String ed2;

    private String music1;

    private String music2;

    private String shows1;

    private String shows2;

    private String sports1;

    private String sports2;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getwork1() {
        return work1;
    }

    public void setwork1(String work1) {
        this.work1 = work1;
    }

    public String getwork2() {
        return work2;
    }

    public void setwork2(String work2) { this.work2 = work2; }

    public String ed1() {
        return ed1;
    }

    public void ed1(String ed1) { this.ed1 = ed1; }

    public String ed2() {
        return ed2;
    }

    public void ed2(String ed2) { this.ed2 = ed2; }

    public String music1() {
        return music1;
    }

    public void music1(String music1) {
        this.music1 = music1;
    }

    public String music2() {
        return music2;
    }

    public void music2(String music1) { this.music2 = music2; }

    public String shows1() {
        return shows1;
    }

    public void shows1(String shows1) { this.shows1 = shows1; }

    public String shows2() {
        return shows2;
    }

    public void shows2(String shows2) { this.shows2 = shows2; }

    public String sports1() {
        return sports1;
    }

    public void sports1(String sports1) { this.sports1 = sports1; }

    public String sports2() {
        return sports2;
    }

    public void sports2(String sports2) { this.sports2 = sports2; }


}