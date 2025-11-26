package ex1;

import java.sql.*;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/`new_schemal8`";

        Connection connection = DriverManager.getConnection(url, "root", "root");
   //     Statement statement = connection.createStatement();
     //   ResultSet rs = statement.executeQuery(sql);
 //       while (rs.next())
   //         System.out.println("id=" + rs.getInt("Id") + ", nume= "
     //               + rs.getString("nume") + ",varsta=" + rs.getInt("varsta"));
    //    Scanner sc = new Scanner(System.in);

        Statement sql = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        );
        ResultSet rs=sql.executeQuery("select * from persoane");
       rs.moveToInsertRow();
       rs.updateString("nume","Ana");
       rs.updateInt(3,14);
       rs.insertRow();

       rs.beforeFirst();
       while (rs.next())
           System.out.println("id="+rs.getInt(1)+", nume= "+rs.getString());




}
