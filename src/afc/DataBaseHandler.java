/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.ResultSetMetaData;
import java.io.IOException;

/**
 *
 * @author LENOVO
 */
public class DataBaseHandler {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/afc";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    private static Connection connection = null;
    public static Connection dbConnection(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            return connection;
        }catch(Exception e){
            System.out.println("DATABASE connection error!!");
            return null;
        }
    }
}
