package database.example;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import database.pojo.Brand;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 完成商品品牌数据的增删改查操作
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
        Properties pro = new Properties();
        pro.load(new FileInputStream("D:\\19578\\IdeaProjects\\database\\src\\database\\druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pro);
        Connection conn = dataSource.getConnection();
        //2.定义SQL语句
        String sql = "select * from tb_brand";
        //3.获取预编译的数据库操作对象pstmt
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //4.设置参数

        //5.执行SQL
        ResultSet rs = pstmt.executeQuery();
        //6.处理结果 List<Brand> 封装Brand对象, 装载List集合
        Brand brand = null;
        List<Brand> brandList = new ArrayList<>();
        while (rs.next()) {
            //获取数据
            String id = rs.getString("id");
            String brandName = rs.getString("brand_name");
            String companyName = rs.getString("company_name");
            String ordered = rs.getString("ordered");
            String description = rs.getString("description");
            String status = rs.getString("status");
            //封装Brand对象
            brand = new Brand(Integer.valueOf(id), brandName, companyName, Integer.valueOf(ordered), description, Integer.valueOf(status));
            //装载集合
            brandList.add(brand);
        }
        //打印查询结果集
        for (Brand brands : brandList) {
            System.out.println(brands);
        }
        //7.释放资源
        if (rs != null) {
            rs.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * 添加
     * 1.SQL：insert into tb_brand(brand_name,company_name,ordered,description,status)values(?,?,?,?,?)
     * 2.参数：需要，除了id以外的所有参数信息
     * 3.结果：boolean
     *
     * @throws Exception
     */
    @Test
    public void testAdd() throws Exception {
        //接收页面提交的数据
        String brandName = "蜜雪冰城";
        String companyName = "浙江餐饮有限公司";
        int ordered = 120;
        String description = "蜜雪冰城甜蜜蜜";
        int status = 1;

        //1.获取Connection
        Properties pro = new Properties();
        pro.load(new FileInputStream("D:\\19578\\IdeaProjects\\database\\src\\database\\druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pro);
        Connection conn = dataSource.getConnection();
        //2.定义SQL语句
        String sql = "insert into tb_brand(brand_name,company_name,ordered,description,status)values(?,?,?,?,?)";
        //3.获取预编译的数据库操作对象pstmt
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //4.设置参数
        pstmt.setString(1, brandName);
        pstmt.setString(2, companyName);
        pstmt.setInt(3, ordered);
        pstmt.setString(4, description);
        pstmt.setInt(5, status);

        //5.执行SQL
        int count = pstmt.executeUpdate();//影响的行数
        //6.处理结果
        System.out.println(count>0);

        //7.释放资源
        if (pstmt != null) {
            pstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * 修改
     * 1.SQL: update  tb_brand set brand_name=?,company_name=?,ordered=?,description=?,status=? where id=?
     * 2.参数：需要，所有的数据
     * 3.结果：boolean
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        //更新提交过的页面数据
        String brandName = "农夫山泉";
        String companyName = "上海饮品股份有限公司";
        int ordered = 30;
        String description = "大自然的搬运工";
        int status = 1;
        int id=4;
        //1.获取Connection
        Properties pro = new Properties();
        pro.load(new FileInputStream("D:\\19578\\IdeaProjects\\database\\src\\database\\druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pro);
        Connection conn = dataSource.getConnection();
        //2.定义SQL语句
        String sql = "update  tb_brand set brand_name=?,company_name=?,ordered=?,description=?,status=? where id=?";
        //3.获取预编译的数据库操作对象pstmt
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //4.设置参数
        pstmt.setString(1, brandName);
        pstmt.setString(2, companyName);
        pstmt.setInt(3, ordered);
        pstmt.setString(4, description);
        pstmt.setInt(5, status);
        pstmt.setInt(6, id);
        //5.执行SQL
        int count = pstmt.executeUpdate();//影响的行数
        //6.处理结果
        System.out.println(count>0);
        //7.释放资源
        if (pstmt != null) {
            pstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    /**
     * 删除
     * 1.SQL: delete from tb_brand where id=?
     * 2.参数：需要，id
     * 3.结果：boolean
     * @throws Exception
     */
    @Test
    public void testDel() throws Exception {
        //删除提交过的页面数据
        int id=5;
        //1.获取Connection
        Properties pro = new Properties();
        pro.load(new FileInputStream("D:\\19578\\IdeaProjects\\database\\src\\database\\druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pro);
        Connection conn = dataSource.getConnection();
        //2.定义SQL语句
        String sql = "delete from tb_brand where id=?";
        //3.获取预编译的数据库操作对象pstmt
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //4.设置参数
        pstmt.setInt(1, id);
        //5.执行SQL
        int count = pstmt.executeUpdate();//影响的行数
        //6.处理结果
        System.out.println(count>0);
        //7.释放资源
        if (pstmt != null) {
            pstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
