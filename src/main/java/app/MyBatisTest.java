package app;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.Reader;

public class MyBatisTest {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader cfg = Resources.getResourceAsReader("config.xml");
            if(sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(cfg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
