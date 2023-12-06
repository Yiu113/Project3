package com.example.project2;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tenant {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phoneNum;
    private String checkIn;
    private String checkOut;
    private int apartmentNum;

    protected Tenant() {}



    public Tenant(String name, String email, String phoneNum, String checkIn, String checkOut, int apartmentNum) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.apartmentNum = apartmentNum;
    }

    @Override
    public String toString() {
        return String.format(
                "Tenant[id=%d, name='%s', email='%s', phone number='%s', check in='%s', check out ='%s', apartment number='%d']",
                id, name, email, phoneNum, checkIn, checkOut, apartmentNum);
    }

    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail() {return email;}
    public void setEmail(String email){this.email = email;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getApartmentNum() {
        return apartmentNum;
    }

    public void setApartmentNum(int apartmentNum) {
        this.apartmentNum = apartmentNum;
    }


}
