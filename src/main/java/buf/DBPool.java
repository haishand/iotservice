package buf;

import pojo.DeviceInfo;

import java.util.concurrent.LinkedBlockingQueue;

public class DBPool {
    private LinkedBlockingQueue<DeviceInfo> q;
    private static DBPool instance = null;

    private DBPool() {
        q = new LinkedBlockingQueue<DeviceInfo>();
    }

    public static DBPool instance() {
        if(instance == null) {
            instance = new DBPool();
        }
        return instance;
    }

    public LinkedBlockingQueue<DeviceInfo> getQ() {
        return q;
    }
}
