package JDBCTest;

import java.util.Scanner;
import java.sql.*;

/**
 * @Description:
 * @User:
 * @Date:
 */
public class JDBCTest08 {
    public static void main(String[] args) {
        //用户在控制台输入desc就是降序，输入asc就是升序
        Scanner s = new Scanner(System.in);
        System.out.println("请输入desc或asc,desc表示降序,asc表示升序");
        System.out.print("请输入:");
        String KeyWords = s.nextLine();

        //执行SQL
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取链接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jackson","root","Lfh5340.0");
            //获取数据库操作对象
            stmt = conn.createStatement();
            //执行SQL
            String sql = "select ename from emp order by ename " + KeyWords;
            rs = stmt.executeQuery(sql);
            //遍历结果集
            while (rs.next()){
                System.out.println(rs.getString("ename"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
