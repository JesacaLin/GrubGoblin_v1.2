package org.JesacaLin.models;

public class Place {
    private int placeId;
    private String placeName;
    private String address;
    private double latitude;
    private double longitude;
    private double googleRating;

    public Place() {
    }

    public Place(int placeId, String placeName, String address, double latitude, double longitude, double googleRating) {
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

    public void setPlaceId(String errorMessage, int placeId) {
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

    public void setLatitude(String unableToSetLatitude, double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(String unableToSetLongitude, double longitude) {
        this.longitude = longitude;
    }

    public double getGoogleRating() {
        return googleRating;
    }

    public void setGoogleRating(String unableToSetGoogleRating, double googleRating) {
        this.googleRating = googleRating;
    }

    @Override
    public String toString() {
        return "Place {" +
                " ID = " + placeId +
                ", NAME = " + placeName +
                ", ADDRESS = " + address +
                ", LATITUDE = " + latitude +
                ", LONGITUDE = " + longitude +
                ", GOOGLE RATING = " + googleRating +
                " }";
    }
}
