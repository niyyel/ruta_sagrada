package com.example.djnig.tantamayo.Entity;

public class DescriptionPlaceClass {

    public String place="";
    public String schedule="";
    public String ubication="";
    public String history="";
    public String access="";
    public int imagenPlace=-1;
    public int videoPlace=-1;

    public DescriptionPlaceClass() {

    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public int getImagenPlace() {
        return imagenPlace;
    }

    public void setImagenPlace(int imagenPlace) {
        this.imagenPlace = imagenPlace;
    }

    public int getVideoPlace() {
        return videoPlace;
    }

    public void setVideoPlace(int videoPlace) {
        this.videoPlace = videoPlace;
    }

    @Override
    public String toString() {
        return "DescriptionPlaceClass{" +
                "place='" + place + '\'' +
                ", schedule='" + schedule + '\'' +
                ", ubication='" + ubication + '\'' +
                ", history='" + history + '\'' +
                ", imagenPlace=" + imagenPlace +
                ", videoPlace=" + videoPlace +
                '}';
    }
}
