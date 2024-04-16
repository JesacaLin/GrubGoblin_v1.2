package org.JesacaLin.models;

public class place {
    private int placeId;
    private String placeName;
    private String address;
    private double latitude;
    private double longitude;
    private double googleRating;

    public place() {
    }

    public place(int placeId, String placeName, String address, double latitude, double longitude, double googleRating) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.googleRating = googleRating;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getGoogleRating() {
        return googleRating;
    }

    public void setGoogleRating(double googleRating) {
        this.googleRating = googleRating;
    }

    @Override
    public String toString() {
        return "Place {" +
                "id = " + placeId +
                ", name = " + placeName +
                ", latitude = " + latitude +
                ", longitude = " + longitude +
                ", Google rating = " + googleRating +
                "}";
    }
}
