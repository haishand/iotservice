package app;

import dao.DeviceInfoDao;
import pojo.DeviceInfo;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DeviceInfoDao dao = new DeviceInfoDao(MyBatisTest.getSqlSessionFactory());
        List<DeviceInfo> list = dao.selectAll();
        for(DeviceInfo info : list) {
            System.out.println(info.getId());
            System.out.println(info.getStatus());
        }

        DeviceInfo info = new DeviceInfo();
        info.setHum(10.1f);
        info.setTem(20.2f);
        info.setId(43543543);
        info.setStatus("00000000");

        dao.addOne(info);
    }
}
