package JDBCTest;

import java.sql.*;

/**
 * @Description:
 *
 * 编程六步：
 *
 * @User:
 * @Date:
 */
public class JDBCTest01 {
    public static void main(String[] args) {
        Driver driver = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            //1、注册驱动
            driver = new com.mysql.cj.jdbc.Driver();//多态，父类型引用指向子类型对象
            DriverManager.registerDriver(driver);
            //2、获取连接
            /*
                url:统一资源定位符（网络中某个资源的绝对路径）
                URL包括哪几部分？
                    协议
                    IP
                    PORT
                    资源
                jdbc:mysql://127.0.0.1:3306/jackson
                    jdbc:mysql://协议
                    127.0.0.1 IP地址
                    3306 mysql数据库端口号
                    jackson 具体的数据库实例名
                说明：localhost和127.0.0.1都是本机IP地址。
                什么是通信协议，有什么用？
                    通信协议是通信之前就提前定好的数据传送格式。
                    数据包具体怎么传数据，格式提前定好的
             */
            String url = "jdbc:mysql://localhost:3306/jackson";
            String user = "root";
            String password = "Lfh5340.0";
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("数据库连接对象 = " + conn);
            //com.mysql.cj.jdbc.ConnectionImpl@139982de

            //3、获取数据库操作对象
            stmt = conn.createStatement();

            //4、执行sql
            String sql = "insert into dept(deptno,dname,loc) values(50,'人事部','北京')";
            //专门执行DML语句的(insert delete update)
            //返回值是“影响数据库中的记录条数”
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1? "保存成功" : "保存失败");

            //5、处理查询结果集

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //6、释放资源
            //为了保证资源一定释放，在finally语句块中关闭资源
            //并且要遵循从小到大依次关闭
            //分别对其try...catch
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
