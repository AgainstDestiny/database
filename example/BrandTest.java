package database.example;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import database.pojo.Brand;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 品牌数据的增删改查操作
 *
 * @Description: database.example
 * @author: Axu
 * @date:2022/6/16 16:53
 */
public class BrandTest {
    /**
     * 查询所有
     * 1.SQL:select * from tb_brand
     * 2.参数：目前不需要
     * 3.结果：List<Brand></>
     */
    @Test
    public void testSelectAll() throws Exception {
        //1.获取Connection
        Properties pro=new Properties();
        pro.load(new FileInputStream("D:\\19578\\IdeaProjects\\database\\src\\database\\druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pro);
        Connection conn = dataSource.getConnection();
        //2.定义SQL语句
        String sql="select * from tb_brand";
        //3.获取预编译的数据库操作对象pstmt
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //4.设置参数

        //5.执行SQL
        ResultSet rs = pstmt.executeQuery();
        //6.处理结果 List<Brand> 封装Brand对象, 装载List集合
        while (rs.next()){
            //获取数据
            String id = rs.getString("id");
            String brandName = rs.getString("brand_name");
            String companyName = rs.getString("company_name");
            String ordered = rs.getString("ordered");
            String description = rs.getString("description");
            String status = rs.getString("status");
            //封装Brand对象
            Brand brand=new Brand(Integer.valueOf(id), brandName, companyName, Integer.valueOf(ordered), description, Integer.valueOf(status));
            //装载集合
            List<Brand> brandList=new ArrayList<Brand>();
            brandList.add(brand);
            //打印查询结果集
            for (Brand brands : brandList) {
                System.out.println(brands);
            }
        }
        //7.释放资源
        rs.close();
        pstmt.close();
        conn.close();




    }
}
