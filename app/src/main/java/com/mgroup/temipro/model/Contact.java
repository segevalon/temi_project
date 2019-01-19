package com.mgroup.temipro.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Contact implements Comparable<Contact> {

    private long lastMessageTs;
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("gender")
    private String gender;
    @SerializedName("email")
    private String email;
    @SerializedName("Address")
    private String Address;
    @SerializedName("avatar")
    private String avatar;


    public Contact(String ID, String firstName, String lastName, String phone, String gender, String email, String address, String avatarUrl) {
        this.id = ID;
        this.name = firstName + " " + lastName;
        this.first_name = firstName;
        this.last_name = lastName;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
        this.Address = address;
        this.avatar = avatarUrl;
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public String getAvatarUrl() {
        return avatar;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatar = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public long getLastMessageTs() {
        return lastMessageTs;
    }

    public void setLastMessageTs(long lastMessageTs) {
        this.lastMessageTs = lastMessageTs;
    }

    @Override
    public int compareTo(@NonNull Contact o) {
        return this.getName().compareTo(o.getName());
    }
}
