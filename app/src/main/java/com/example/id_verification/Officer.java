package com.example.id_verification;

public class Officer
{


    private String objectId;
    private String type;
    private String name;
    private String surname;
    private String middleName;
    private String title;
    private int ID;
    private String gender;
    private String rank;
    private String province;
    private String station;
    private String picture;
    private String email;

    //constructor

    public Officer(String objectId, String type, String name, String surname, String middleName,
                   String title, int ID, String gender, String rank, String province,
                   String station, String picture, String email)
    {
        this.objectId = objectId;
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.title = title;
        this.ID = ID;
        this.gender = gender;
        this.rank = rank;
        this.province = province;
        this.station = station;
        this.picture = picture;
        this.email = email;
    }

    //getters and setters

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
