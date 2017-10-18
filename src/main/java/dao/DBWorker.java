package dao;

import util.MyBatisUtils;
import buf.DBPool;
import pojo.DeviceInfo;

public class DBWorker extends Thread{
    private boolean isRunning = true;
    private DeviceInfoDao dao = new DeviceInfoDao(MyBatisUtils.getSqlSessionFactory());  ;

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
