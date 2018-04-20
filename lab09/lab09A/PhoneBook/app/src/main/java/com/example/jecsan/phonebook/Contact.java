package com.example.jecsan.phonebook;

/**
 * Created by jecsan on 4/19/18.
 */

public class Contact {
    private  String fName , lName, phoneNumber;
    Contact(String fName, String lName, String phoneNumber){
        setfName(fName);
        setlName(lName);
        setPhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
}
