package com.example.talia.android5778_5956_6419_02.models.entities;


/**
 * Created by talia on 14/01/2018.
 */

public class Customer {
    protected String lastName;
    protected String firstName;
    protected int id;
    protected String password;
    protected int phoneNumber;
    protected String mail;
    protected int creditCard;


    public Customer() {
        this.lastName = "";
        this.firstName = "";
        this.id = 0;
        this.password="";
        this.phoneNumber = 0;
        this.mail = "";
        this.creditCard = 0;

    }
    public Customer(String lastName, String firstName, int id, String password, int phoneNumber, String mail, int creditCard) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
        this.password=password;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.creditCard = creditCard;

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", id=" + id +
                ", password=" + password +
                ", phoneNumber=" + phoneNumber +
                ", mail='" + mail + '\'' +
                ", creditCard=" + creditCard +
                '}';
    }
}

