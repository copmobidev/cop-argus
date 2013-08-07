package com.cop.argus.car.entity;

import com.google.gson.annotations.Expose;

/**
 * @author chris.liu
 */
public class Battery {
    @Expose
    private double bat; // 电瓶电压
    @Expose
    private long timestamp; // 时间戳

    public Battery() {

    }

    public Battery(double bat, long timestamp) {
        this.bat = bat;
        this.timestamp = timestamp;
    }

    public double getBat() {
        return bat;
    }

    public void setBat(double bat) {
        this.bat = bat;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void add(Battery battery) {
        bat = (bat + battery.getBat()) / 2;
        timestamp = timestamp > battery.getTimestamp() ? battery.getTimestamp()
                : timestamp;
    }
}
