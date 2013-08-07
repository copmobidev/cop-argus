package com.cop.argus.account.service.entity;

/**
 * @author chris.liu
 */
public class UserPo {
    private int id; // 用户ID
    private String obd; // OBD序列号
    private String vin; // VIN
    private String calid; // CALID
    private String cid; // 车辆唯一标识
    private int bid; // 车辆品牌id
    private String email; // 邮箱
    private String name; // 用户名
    private String pwd; // 用户密码
    private int sex; // 性别
    private long birth; // 生日
    private String profile; // 头像URL
    private int score; // 积分
    private int level; // 等级
    private long registerTime; // 注册时间
    private long lstUpdateTime; // 最近修改资料时间

    public UserPo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getCalid() {
        return calid;
    }

    public void setCalid(String calid) {
        this.calid = calid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getBirth() {
        return birth;
    }

    public void setBirth(long birth) {
        this.birth = birth;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public long getLstUpdateTime() {
        return lstUpdateTime;
    }

    public void setLstUpdateTime(long lstUpdateTime) {
        this.lstUpdateTime = lstUpdateTime;
    }

}
