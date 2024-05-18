import java.sql.*;
public class Connection {
    private String uname,upass;
    private int uid,sum;
    public boolean in = false;
    public Connection(String uname){
        this.uname=uname;
    }
    public int validate() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("加载成功");
        } catch (Exception ex) {
            System.out.println("加载失败");
        }
        //getConnection()方法，连接MySQL数据库！！
        try(java.sql.Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/server?serverTimezone=GMT%2B8","root","123456")) {
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            statement.execute("begin;");
            //要执行的SQL语句
            String sql = "select * from user where username='" + uname + "'";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    in = true;
                    int uid = rs.getInt("userid");
                    this.uid = uid;
                    String upass = rs.getString("userpass");
                    this.upass = upass;
                    int sum = rs.getInt("sum");
                    this.sum = sum;
                }
            statement.execute("commit;");
        } catch(SQLException e1) {
            //数据库连接失败异常处理
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return this.uid;
    }
    public String getUpass(){
        return upass;
    }
    public int getSum(){
        return sum;
    }
}
