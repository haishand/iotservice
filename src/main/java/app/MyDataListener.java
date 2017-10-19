package app;

import buf.DBPool;
import com.jnrsmcu.sdk.netdevice.*;
import pojo.DeviceInfo;

import java.util.Date;

public class MyDataListener implements IDataListener {
    private static float EPS = 0.001f;

    @Override
    public void receiveRealtimeData(RealTimeData realTimeData) {
        for(NodeData nd : realTimeData.getNodeList()) {
            int id = realTimeData.getDeviceId();
            float tem = nd.getTem();
            float hum = nd.getHum();
            Date time = nd.getRecordTime();
            String status = realTimeData.getRelayStatus();

//System.out.println(id + ":" + tem + ":" + hum + ":" + status);
            // skip 2 empty device record
            if (tem < EPS || hum < EPS) {
                continue;
            }
            DBPool.instance().getQ().offer(new DeviceInfo(id, status, tem, hum, time));

        }

    }

    @Override
    public void receiveLoginData(LoginData loginData) {

    }

    @Override
    public void receiveStoreData(StoreData storeData) {

    }

    @Override
    public void receiveTelecontrolAck(TelecontrolAck telecontrolAck) {

    }

    @Override
    public void receiveTimmingAck(TimmingAck timmingAck) {

    }
}
