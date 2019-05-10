package com.validity.models;

import lombok.Data;

import java.util.Arrays;

@Data
public class User {
    private long userId;
    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String addressOne;
    private String addressTwo;
    private String city;
    private String stateShort;
    private String stateLong;
    private String phone;
    private int zipCode;
    private int dupScore;
    private int fullness;

    public User() {
    }

    public static class UserBuilder {
        private long userId;
        private String firstName;
        private String lastName;
        private String company;
        private String email;
        private String addressOne;
        private String addressTwo;
        private String city;
        private String stateShort;
        private String stateLong;
        private String phone;
        private int zipCode;

        public UserBuilder() {
        }

        public User build() {
            User user = new User();
            user.userId = this.userId;
            user.firstName = this.firstName;
            user.lastName = this.lastName;
            user.company = this.company;
            user.email = this.email;
            user.addressOne = this.addressOne;
            user.addressTwo = this.addressTwo;
            user.city = this.city;
            user.stateShort = this.stateShort;
            user.stateLong = this.stateLong;
            user.zipCode = this.zipCode;
            user.phone = this.phone;
            return user;
        }

        public UserBuilder withUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder withCompany(String company) {
            this.company = company;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withAddressOne(String addressOne) {
            this.addressOne = addressOne;
            return this;
        }

        public UserBuilder withAddressTwo(String addressTwo) {
            this.addressTwo = addressTwo;
            return this;
        }

        public UserBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public UserBuilder withStateShort(String stateShort) {
            this.stateShort = stateShort;
            return this;
        }

        public UserBuilder withStateLong(String stateLong) {
            this.stateLong = stateLong;
            return this;
        }

        public UserBuilder withZipCode(int zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public UserBuilder withPhoneNumber(String phone) {
            this.phone = phone;
            return this;
        }
    }
}
