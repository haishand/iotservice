package dao;

import app.MyBatisTest;
import buf.DBPool;
import pojo.DeviceInfo;

public class DBWorker implements Runnable{
    private boolean isRunning = true;
    private DeviceInfoDao dao = new DeviceInfoDao(MyBatisTest.getSqlSessionFactory());  ;

    public DBWorker() {
    }

    public void run() {
        while(isRunning) {
            DeviceInfo record = DBPool.instance().getQ().poll();
            if(record != null)
                dao.addOne(record);
        }
    }
}
