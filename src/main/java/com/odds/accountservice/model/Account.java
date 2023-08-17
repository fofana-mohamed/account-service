package com.odds.accountservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "Accounts")
@Getter
@Setter
public class Account {

    @GeneratedValue
    @Id
    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String bankAccountNumber;

    public Account(String firstName, String lastName, String email, String phoneNumber, String bankAccountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bankAccountNumber = bankAccountNumber;
    }

    public Account() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(Id, account.Id) && Objects.equals(firstName, account.firstName) && Objects.equals(lastName, account.lastName) && Objects.equals(email, account.email) && Objects.equals(phoneNumber, account.phoneNumber) && Objects.equals(bankAccountNumber, account.bankAccountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, firstName, lastName, email, phoneNumber, bankAccountNumber);
    }

    @Override
    public String toString() {
        return "Account{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                '}';
    }
}
