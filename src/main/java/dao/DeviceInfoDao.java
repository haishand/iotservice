package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.DeviceInfo;

import java.util.List;

public class DeviceInfoDao {
    private SqlSessionFactory sqlSessionFactory = null;

    public DeviceInfoDao(SqlSessionFactory factory) {
        this.sqlSessionFactory = factory;
    }

    public List<DeviceInfo> selectAll() {
        List<DeviceInfo> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        list = session.selectList("DeviceInfo.selectAll");
        session.close();

        return list;
    }

    public int addOne(DeviceInfo info) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            id = session.insert("DeviceInfo.addOne", info);
        }finally {
            session.commit();;
            session.close();
        }
        return id;
    }
}
