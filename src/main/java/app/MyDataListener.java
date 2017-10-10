package app;

import buf.DBPool;
import buf.MyDBRecord;
import com.jnrsmcu.sdk.netdevice.*;

public class MyDataListener implements IDataListener {
    private static float EPS = 0.001f;

    public void receiveRealtimeData(RealTimeData realTimeData) {
        for(NodeData nd : realTimeData.getNodeList()) {
            int id = realTimeData.getDeviceId();
            float tem = nd.getTem();
            float hum = nd.getHum();
            String status = realTimeData.getRelayStatus();

            // skip empty 2 device record
            if(tem < EPS || hum < EPS) continue;
            DBPool.q.offer(new MyDBRecord(id, tem, hum, status));

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
