package org.JesacaLin.models;

public class Review {
    private int reviewId;
    private int dealId;
    private double stars;
    private String reviewDescription;

    public Review() {
    }

    public Review(int reviewId, int dealId, double stars, String reviewDescription) {
        this.reviewId = reviewId;
        this.dealId = dealId;
        this.stars = stars;
        this.reviewDescription = reviewDescription;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(String unableToSetReviewId, int reviewId) {
        this.reviewId = reviewId;
    }

    public int getDealId() {
        return dealId;
    }

    public void setDealId(String unableToSetDealId, int dealId) {
        this.dealId = dealId;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(String unableToSetStarRating, double stars) {
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
        return "Review {" +
                " ID = " + reviewId +
                ", DEAL ID = " + dealId +
                ", STARS = " + stars +
                ", REVIEW DESCRIPTION = " + reviewDescription +
                " }";
    }
}
