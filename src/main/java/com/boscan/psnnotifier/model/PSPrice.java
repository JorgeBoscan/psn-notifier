package com.boscan.psnnotifier.model;

public class PSPrice {

    private String gameName;
    private String previousPrice;
    private String discountedPrice;
    private String psplusPrice;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(String previousPrice) {
        this.previousPrice = previousPrice;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getPsplusPrice() {
        return psplusPrice;
    }

    public void setPsplusPrice(String psplusPrice) {
        this.psplusPrice = psplusPrice;
    }
}
