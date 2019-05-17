package com.depixel.web.tantamayo.Entity;

public class CraftClass {
        private int photoCraft=-1;
        private String nameCraft="";
        private String scheduleraft="";
        private String numberCraft="";
        private String idCraft="";

    public CraftClass(int photoCraft, String nameCraft, String scheduleraft, String numberCraft, String idCraft) {
        this.photoCraft = photoCraft;
        this.nameCraft = nameCraft;
        this.scheduleraft = scheduleraft;
        this.numberCraft = numberCraft;
        this.idCraft = idCraft;
    }

    public int getPhotoCraft() {
        return photoCraft;
    }

    public void setPhotoCraft(int photoCraft) {
        this.photoCraft = photoCraft;
    }

    public String getNameCraft() {
        return nameCraft;
    }

    public void setNameCraft(String nameCraft) {
        this.nameCraft = nameCraft;
    }

    public String getScheduleraft() {
        return scheduleraft;
    }

    public void setScheduleraft(String scheduleraft) {
        this.scheduleraft = scheduleraft;
    }

    public String getNumberCraft() {
        return numberCraft;
    }

    public void setNumberCraft(String numberCraft) {
        this.numberCraft = numberCraft;
    }

    public String getIdCraft() {
        return idCraft;
    }

    public void setIdCraft(String idCraft) {
        this.idCraft = idCraft;
    }

    @Override
    public String toString() {
        return "CraftClass{" +
                "photoCraft=" + photoCraft +
                ", nameCraft='" + nameCraft + '\'' +
                ", scheduleraft='" + scheduleraft + '\'' +
                ", numberCraft='" + numberCraft + '\'' +
                ", idCraft='" + idCraft + '\'' +
                '}';
    }
}
