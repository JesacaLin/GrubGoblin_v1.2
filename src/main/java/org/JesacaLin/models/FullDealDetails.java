package org.JesacaLin.models;

import java.time.LocalTime;

public class FullDealDetails {
    private String placeName;
    private String address;
    private String typeOfDeal;
    private String dealDescription;
    private int dayOfWeek;
    private LocalTime startTime;
    private double stars;
    private String reviewDescription;

    public FullDealDetails() {
    }

    public FullDealDetails(String placeName, String address, String typeOfDeal, String dealDescription, int dayOfWeek, LocalTime startTime, double stars, String reviewDescription) {
        this.placeName = placeName;
        this.address = address;
        this.typeOfDeal = typeOfDeal;
        this.dealDescription = dealDescription;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.stars = stars;
        this.reviewDescription = reviewDescription;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypeOfDeal() {
        return typeOfDeal;
    }

    public void setTypeOfDeal(String typeOfDeal) {
        this.typeOfDeal = typeOfDeal;
    }

    public String getDealDescription() {
        return dealDescription;
    }

    public void setDealDescription(String dealDescription) {
        this.dealDescription = dealDescription;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    @Override
    public String toString() {
        return "Deal Detail {" +
                " PLACE NAME = " + placeName +
                ", ADDRESS = " + address +
                ", TYPE OF DEAL = " + typeOfDeal +
                ", DEAL DESCRIPTION = " + dealDescription +
                ", DAY OF THE WEEK = " + dayOfWeek +
                ", START TIME = " + startTime +
                ", DEAL RATING = " + stars +
                ", REVIEW = " + reviewDescription +
                " }";
    }
}
