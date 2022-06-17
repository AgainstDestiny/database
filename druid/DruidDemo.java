package database.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;


import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Druid数据库连接池演示
 * @Description: database.druid
 * @author: Axu
 * @date:2022/6/16 15:43
 */
public class DruidDemo {
    public static void main(String[] args) throws Exception {
       //1.导入jar包

        //2.定义配置文件

        //3.加载配置文件
        Properties pro=new Properties();
        pro.load(new FileInputStream("D:\\19578\\IdeaProjects\\database\\src\\database\\druid.properties"));
        //4.获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pro);

        //5.获取数据库连接 Connection
        Connection connection = dataSource.getConnection();

        System.out.println(connection);
    }
}
