package com.cop.argus.car.entity;

/**
 * @author chris.liu
 */
public class OBDConfig {
    private String obd;
    private String vin;
    private String cid;
    private boolean fuel; // 油耗PID是否存在
    private boolean map; //
    private boolean maf; //
    private boolean fuelCal; // 加油统计方式(true:直接读取;false:POI加油统计)
    private boolean dist; // 里程是否可校准
    private String lstFileName; // 系统最后记录文件名
    private String calid;

    public String getObd() {
        return obd;
    }

    public void setObd(String obd) {
        this.obd = obd;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public boolean isFuel() {
        return fuel;
    }

    public void setFuel(boolean fuel) {
        this.fuel = fuel;
    }

    public boolean isMap() {
        return map;
    }

    public void setMap(boolean map) {
        this.map = map;
    }

    public boolean isMaf() {
        return maf;
    }

    public void setMaf(boolean maf) {
        this.maf = maf;
    }

    public boolean isFuelCal() {
        return fuelCal;
    }

    public void setFuelCal(boolean fuelCal) {
        this.fuelCal = fuelCal;
    }

    public boolean isDist() {
        return dist;
    }

    public void setDist(boolean dist) {
        this.dist = dist;
    }

    public String getLstFileName() {
        return lstFileName;
    }

    public void setLstFileName(String lstFileName) {
        this.lstFileName = lstFileName;
    }

    public String getCalid() {
        return calid;
    }

    public void setCalid(String calid) {
        this.calid = calid;
    }
}
