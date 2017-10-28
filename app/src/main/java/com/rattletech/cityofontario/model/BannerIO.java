package com.rattletech.cityofontario.model;

/**
 * Created by mahendran on 24/10/17.
 */

public class BannerIO {

    String bannerName;
    String bannerDate;
    String bannerImageLink;

    public String getBannerImageLink() {
        return bannerImageLink;
    }

    public void setBannerImageLink(String bannerImageLink) {
        this.bannerImageLink = bannerImageLink;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getBannerDate() {
        return bannerDate;
    }

    public void setBannerDate(String bannerDate) {
        this.bannerDate = bannerDate;
    }
}
