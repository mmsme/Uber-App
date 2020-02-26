package com.m_mustafa.softwareproject;

public class HistoryData {
    String driverName;
    String tripStratPoint;
    String tripEndPoint;
    String date;
    int driverImg;

    public HistoryData(String driverName, String tripStratPoint, String tripEndPoint, String data, int driverImg) {
        this.driverName = driverName;
        this.tripStratPoint = tripStratPoint;
        this.tripEndPoint = tripEndPoint;
        this.date = data;
        this.driverImg = driverImg;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getTripStratPoint() {
        return tripStratPoint;
    }

    public void setTripStratPoint(String tripStratPoint) {
        this.tripStratPoint = tripStratPoint;
    }

    public String getTripEndPoint() {
        return tripEndPoint;
    }

    public void setTripEndPoint(String tripEndPoint) {
        this.tripEndPoint = tripEndPoint;
    }

    public String getData() {
        return date;
    }

    public void setData(String data) {
        this.date = data;
    }

    public int getDriverImg() {
        return driverImg;
    }

    public void setDriverImg(int driverImg) {
        this.driverImg = driverImg;
    }
}
