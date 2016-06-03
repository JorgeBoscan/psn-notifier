package com.boscan.psnnotifier.model;

import java.util.List;

public class Member {

    private String email;
    private boolean announcedFlashSale;
    private List<Game> games;


    public boolean isAnnouncedFlashSale() {
        return announcedFlashSale;
    }

    public void setAnnouncedFlashSale(boolean announcedFlashSale) {
        this.announcedFlashSale = announcedFlashSale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
