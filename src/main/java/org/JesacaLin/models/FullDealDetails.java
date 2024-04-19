package org.JesacaLin.models;

import java.time.LocalTime;

public class FullDealDetails {
    private int dealId;
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

    public FullDealDetails(int dealId, String placeName, String address, String typeOfDeal, String dealDescription, int dayOfWeek, LocalTime startTime, double stars, String reviewDescription) {
        this.dealId = dealId;
        this.placeName = placeName;
        this.address = address;
        this.typeOfDeal = typeOfDeal;
        this.dealDescription = dealDescription;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.stars = stars;
        this.reviewDescription = reviewDescription;
    }

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
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
        return "Deal Detail {\n" +
                "   DEAL ID = " + dealId + ",\n" +
                "   PLACE NAME = " + placeName + ",\n" +
                "   ADDRESS = " + address + ",\n" +
                "   TYPE OF DEAL = " + typeOfDeal + ",\n" +
                "   DEAL DESCRIPTION = " + dealDescription + ",\n" +
                "   DAY OF THE WEEK = " + dayOfWeek + ",\n" +
                "   START TIME = " + startTime + ",\n" +
                "   DEAL RATING = " + stars + ",\n" +
                "   REVIEW = " + reviewDescription + "\n" +
                "}";
    }

}
