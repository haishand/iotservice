package buf;

public class MyDBRecord {
    int deviceId;
    float tem, hum;
    String status;

    public MyDBRecord(int id, float tem, float hum, String status) {
        this.deviceId = id;
        this.tem = tem;
        this.hum = hum;
        this.status = status;
    }
}
