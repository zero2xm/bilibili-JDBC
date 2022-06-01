package JDBCTest;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * @Description:
 *
 *
 *
 * @User:
 * @Date:
 */
public class JDBCTest05 {
    public static void main(String[] args) {
        ResourceBundle bundle1 = ResourceBundle.getBundle("jdbc");
        String driver = bundle1.getString("driver");
        String url = bundle1.getString("url");
        String user = bundle1.getString("user");
        String password = bundle1.getString("password");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{

            Class.forName(driver);

            conn = DriverManager.getConnection(url,user,password);

            stmt = conn.createStatement();

            String sql = "select empno as a,ename,sal from emp";
            //int executeUpdate(insert/delete/update)
            //ResultSet executeQuery(select)
            rs = stmt.executeQuery(sql);
            //5、处理查询结果集
            /*
            boolean flag1 = rs.next();
            if (flag1){
                //光标指向的行有数据
                //取数据
                //getString()方法的特点是：不管数据库中的数据类型是什么，都以String的形式取出。
                String empno = rs.getString(1);//JDBC中所有下标从1开始，不是从0开始。
                String ename = rs.getString(2);
                String sal = rs.getString(3);
                System.out.println(empno + "," + ename + "," + sal);
            }
            flag1 = rs.next();
            if (flag1){
                //光标指向的行有数据
                //取数据
                //getString()方法的特点是：不管数据库中的数据类型是什么，都以String的形式取出。
                String empno = rs.getString(1);//JDBC中所有下标从1开始，不是从0开始。
                String ename = rs.getString(2);
                String sal = rs.getString(3);
                System.out.println(empno + "," + ename + "," + sal);
            }
             */
            while (rs.next()){
                /*String empno = rs.getString(1);
                String ename = rs.getString(2);
                String sal = rs.getString(3);
                System.out.println(empno + "," + ename + "," + sal);*/

                //这个不是以列的下表获取，以列的名字获取
                //除了可以以String类型取出之外，还可以以特定的类型取出。
                int empno1 = rs.getInt(1);
                String empno = rs.getString("a");
                String ename = rs.getString("ename");
                String sal = rs.getString("sal");
                System.out.println(empno + "," + ename + "," + sal);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {

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
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
