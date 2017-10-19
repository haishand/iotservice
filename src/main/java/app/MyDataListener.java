package app;

import buf.DBPool;
import com.jnrsmcu.sdk.netdevice.*;
import gui.MainFrame;
import org.apache.log4j.Logger;
import pojo.DeviceInfo;

import java.util.Date;

public class MyDataListener implements IDataListener {
    private static Logger logger = Logger.getLogger(MyDataListener.class);
    private static float EPS = 0.001f;

    @Override
    public void receiveRealtimeData(RealTimeData realTimeData) {
        for(NodeData nd : realTimeData.getNodeList()) {
            int id = realTimeData.getDeviceId();
            float tem = nd.getTem();
            float hum = nd.getHum();
            Date time = nd.getRecordTime();
            String status = realTimeData.getRelayStatus();

            // skip 2 empty device record
            if (tem < EPS || hum < EPS) {
                continue;
            }

            logger.info("device id:" + id + "," +
                    "temperature:" + tem + "," +
                    "humidity:" + hum + "," +
                    "status:" + status);
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
