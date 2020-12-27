package com.example.talia.android5778_5956_6419_01.models.entities;

/**
 * Created by talia on 14/11/2017.
 */

public class Customer {

    protected String lastName;
    protected String firstName;
    protected int id;
    protected int phoneNumber;
    protected String mail;
    protected int creditCard;

    public Customer(String lastName, String firstName, int id, int phoneNumber, String mail, int creditCard) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
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
                ", phoneNumber=" + phoneNumber +
                ", mail='" + mail + '\'' +
                ", creditCard=" + creditCard +
                '}';
    }
}
