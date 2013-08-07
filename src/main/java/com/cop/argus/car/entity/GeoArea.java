package com.cop.argus.car.entity;

import com.google.gson.annotations.Expose;

/**
 * @author chris.liu
 */
public class GeoArea {
    @Expose
    private String province;
    @Expose
    private String city;
    @Expose
    private String region;

    public GeoArea(String province, String city, String region) {
        this.province = province;
        this.city = city;
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
