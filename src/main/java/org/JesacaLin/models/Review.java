package org.JesacaLin.models;

public class Review {
    private int ratingId;
    private int dealId;
    private double stars;
    private String reviewDescription;

    public Review() {
    }

    public Review(int ratingId, int dealId, double stars, String reviewDescription) {
        this.ratingId = ratingId;
        this.dealId = dealId;
        this.stars = stars;
        this.reviewDescription = reviewDescription;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
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
        return "Reviews {" +
                " ID = " + ratingId +
                ", DEAL ID = " + dealId +
                ", STARS = " + stars +
                ", REVIEW DESCRIPTION = " + reviewDescription +
                " }";
    }
}
