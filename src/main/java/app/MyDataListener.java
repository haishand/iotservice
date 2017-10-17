package app;

import buf.DBPool;
import com.jnrsmcu.sdk.netdevice.*;
import pojo.DeviceInfo;

import java.util.Date;

public class MyDataListener implements IDataListener {
    private static float EPS = 0.001f;

    public void receiveRealtimeData(RealTimeData realTimeData) {
        for(NodeData nd : realTimeData.getNodeList()) {
            int id = realTimeData.getDeviceId();
            float tem = nd.getTem();
            float hum = nd.getHum();
            Date time = nd.getRecordTime();
            String status = realTimeData.getRelayStatus();

            // skip 2 empty device record
            if(tem < EPS || hum < EPS) continue;
            DBPool.instance().getQ().offer(new DeviceInfo(id, status, tem, hum, time));

        }

    }

    public void receiveLoginData(LoginData loginData) {

    }

    public void receiveStoreData(StoreData storeData) {

    }

    public void receiveTelecontrolAck(TelecontrolAck telecontrolAck) {

    }

    public void receiveTimmingAck(TimmingAck timmingAck) {

    }
}
