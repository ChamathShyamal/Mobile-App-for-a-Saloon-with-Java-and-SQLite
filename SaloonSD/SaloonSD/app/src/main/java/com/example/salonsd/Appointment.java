package com.example.salonsd;

public class Appointment {


    public Appointment() {
        this.userName = "";
        this.time = "";
        this.date = "";
        this.timeSlot = "";

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String userName;
    private String time;
    private String date;

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    private String timeSlot;

    public Appointment(String userName, String time, String date, String timeSlot) {
        this.userName = userName;
        this.time = time;
        this.date = date;
        this.timeSlot = timeSlot;
    }
}
