import java.sql.*;
import java.sql.Connection;

public class Withdraw {
    private int uid,wdr;
    public boolean complete=false;
    public Withdraw(int uid,int wdr){
        this.uid=uid;
        this.wdr=wdr;
        add();
    }
    public void add(){
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/server?serverTimezone=GMT%2B8","root","123456")) {
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            statement.execute("begin;");
            //要执行的SQL语句
            String sql = "insert into detail(time,userid,io) values (NOW(),"+uid+","+wdr+");";
            int rowsAffected = statement.executeUpdate(sql);
            if (rowsAffected > 0) {
                complete = true;
            }
            sql = "select sum from user where userid=" + uid + "";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int sum = rs.getInt("sum");
                ClientHandler.user.setSum(sum);
            }
            statement.execute("commit;");
        } catch(SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
