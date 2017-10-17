package pojo;

import java.util.Date;

public class DeviceInfo {
    private int id;
    private String status;
    private float hum, tem;
    private Date time;

    public DeviceInfo() {

    }

    public DeviceInfo(int id, String status, float hum, float tem, Date time) {
        this.id = id;
        this.status = status;
        this.hum = hum;
        this.tem = tem;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getHum() {
        return hum;
    }

    public void setHum(float hum) {
        this.hum = hum;
    }

    public float getTem() {
        return tem;
    }

    public void setTem(float tem) {
        this.tem = tem;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
