package org.JesacaLin.models;

public class Deal {
    private int dealId;
    private int placeId;
    private String typeOfDeal;
    private String dealDescription;

    public Deal() {
    }
    public Deal(int dealId, int placeId, String typeOfDeal, String dealDescription) {
        this.dealId = dealId;
        this.placeId = placeId;
        this.typeOfDeal = typeOfDeal;
        this.dealDescription = dealDescription;
    }

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
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
    @Override
    public String toString() {
        return "Deal {" +
                " ID = " + dealId +
                ", PLACE ID = " + placeId +
                ", TYPE OF DEAL = " + typeOfDeal +
                ", DEAL DESCRIPTION = " + dealDescription +
                " }";
    }
}
