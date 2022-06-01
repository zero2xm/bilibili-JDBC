package JDBCTest;

import JDBCTest.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 *
 * 这个程序两个任务：
 *      1、测试DBUtil是否好用
 *      2、模糊查询怎么写？
 *
 * @User:
 * @Date:
 */
public class JDBCTest12 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = DBUtil.getConnection();
            //获取预编译的数据库操作对象
            String sql = "select ename from emp where ename like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"_A%");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("ename"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DBUtil.close(conn,ps,rs);
        }
    }
}
