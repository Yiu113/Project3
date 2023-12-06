package com.example.project2;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MaintReq {

    //request ID, apartment number, the area of the problem (e.g., kitchen, bathroom), a brief
    //descrip�on of the problem (e.g. bathtub drain gets stuck, AC does not work), the date and �me of the
    //request, an op�onal photo, and the status of the request

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long ID;
    private int apartNum;
    private String area;
    private String description;
    private String date;
    private String photo;
    private String status;

    protected MaintReq(){};




    public MaintReq(int apartNum, String area, String description, String date, String photo, String status){
        this.apartNum = apartNum;
        this.area = area;
        this.description = description;
        this.date = date;
        this.photo = photo;
        this.status = status;
    }
    @Override
    public String toString() {
        return String.format(
                "Request[id=%d, apartment number='%d', description='%s', date='%s', photo='%s', status='%s']",
                ID, apartNum, description, date, photo, status);
    }

    public Long getID() {
        return ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public int getApartNum() {
        return apartNum;
    }

    public void setApartNum(int apartNum) {
        this.apartNum = apartNum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
