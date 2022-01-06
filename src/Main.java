import java.sql.*;
import java.util.Scanner;

public class Main {
    public static boolean login(String username, String password){
        try{
            Connection con= DriverManager.getConnection("jdbc:sqlite:C:\\Users\\U756534\\TestDB\\testjava.db");
            //con.setAutoCommit(true); vlera default, behet comit vete
            Statement st=con.createStatement();
            //st.execute("drop table user");
            st.execute("create table if not exists user (id INTEGER,username TEXT,password TEXT);");

            //st.execute("insert into user values(1,'Mira','1234');");
            //st.execute("insert into user values(2,'Andi','1234');");
            //st.execute("insert into user values(3,'Jonida','1234');");


            st.execute("select * from user");
            ResultSet resultSet = st.getResultSet();
            while (resultSet.next()){
                //System.out.println(resultSet.getString("username"));
                if (username.equals(resultSet.getString("username"))&password.equals(resultSet.getString("password"))){
                    st.close();
                    con.close();
                    return true;
                }
            }
            st.close();
            con.close();
            return false;
        }
        catch (SQLException e){
            System.out.println("Something went wrong: "+e.getMessage());
            return false;
        }
    }
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        while(true){
            System.out.println("Vendos username dhe passwordin:");
            String username=scn.nextLine();
            String password=scn.nextLine();
                if(scn.nextLine().equals("")){
                    if (login(username,password)) {
                        System.out.println("Logimi ishte me sukses");
                        return;
                    }
                    else System.out.println("Logimi ishte pa sukses! Provo perseri!");
                    }
        }
        }
}
