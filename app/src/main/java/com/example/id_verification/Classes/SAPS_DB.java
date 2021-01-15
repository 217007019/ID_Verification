package com.example.id_verification.Classes;

public class SAPS_DB
{
    private  String Name;
    private String Surname;
    private String ID_Number;
    private  String Picture;
    private  String Record;
    private  String RecordNumber;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getID_Number() {
        return ID_Number;
    }

    public void setID_Number(String ID_Number) {
        this.ID_Number = ID_Number;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getRecord() {
        return Record;
    }

    public void setRecord(String record) {
        Record = record;
    }

    public String getRecordNumber() {
        return RecordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        RecordNumber = recordNumber;
    }
}
