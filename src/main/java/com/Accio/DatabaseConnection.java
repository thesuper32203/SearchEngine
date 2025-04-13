package com.Accio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

        static String variable = System.getenv("password");
        //To get the value of the above example

        static Connection connection = null;

        public static Connection getConnection() throws ClassNotFoundException, SQLException {
            if(connection!= null){
                return connection;
            }
            String user = "root";
            String pwd = "sierra419";
            String db = "searchengineapp1";
            return getConnection(user,pwd,db);
        }

        private static Connection getConnection(String userName, String passWord, String db) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?user=" + userName + "&password=" + passWord);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            return connection;

        }
    }


