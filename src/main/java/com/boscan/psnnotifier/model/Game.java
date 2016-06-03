package com.boscan.psnnotifier.model;

import java.util.Date;

public class Game {

    private String id;
    private String name;
    private float previousPrice;
    private float discountedPrice;
    private float psplusPrice;
    private float lowestPrice;
    private boolean psplusFree;
    private boolean discountedFree;
    private float notifyWhenLowerThan;
    private Date lastUpdateDate;

    public boolean isPsplusFree() {
        return psplusFree;
    }

    public void setPsplusFree(boolean psplusFree) {
        this.psplusFree = psplusFree;
    }

    public boolean isDiscountedFree() {
        return discountedFree;
    }

    public void setDiscountedFree(boolean discountedFree) {
        this.discountedFree = discountedFree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getNotifyWhenLowerThan() {
        return notifyWhenLowerThan;
    }

    public void setNotifyWhenLowerThan(float notifyWhenLowerThan) {
        this.notifyWhenLowerThan = notifyWhenLowerThan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(float previousPrice) {
        this.previousPrice = previousPrice;
    }

    public float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public float getPsplusPrice() {
        return psplusPrice;
    }

    public void setPsplusPrice(float psplusPrice) {
        this.psplusPrice = psplusPrice;
    }

    public float getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(float lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
