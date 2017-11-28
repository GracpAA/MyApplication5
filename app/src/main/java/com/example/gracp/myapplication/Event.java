package com.example.gracp.myapplication;

/**
 * Created by gracp on 17.11.2017.
 */

public class Event {
    private String title;
    private String description;
    private String phone;
    private double v;
    private double v1;

    Event(String description,String phone, String title, double v, double v1){

        this.description = description;
        this.phone = phone;
        this.title = title;
        this.v = v;
        this.v1 = v1;

    }

    Event(){

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getV1() {
        return v1;
    }

    public void setV1(double v1) {
        this.v1 = v1;
    }

    @Override
    public String toString(){
        return title+" "+description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
