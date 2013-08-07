package com.cop.argus.car.entity;

import com.google.gson.annotations.Expose;

/**
 * @author chris.liu
 */
public class GasStationPoi {
    @Expose
    private String name;
    @Expose
    private String address;
    @Expose
    private double lat;
    @Expose
    private double lng;
    @Expose
    private GeoArea geoArea;

    public GasStationPoi(String name, String address, double lat, double lng,
                         GeoArea geoArea) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.geoArea = geoArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public GeoArea getGeoArea() {
        return geoArea;
    }

    public void setGeoArea(GeoArea geoArea) {
        this.geoArea = geoArea;
    }
}
