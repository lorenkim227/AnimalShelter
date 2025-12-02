package edu.loyola.cs485;

import edu.loyola.cs485.model.dao.ClientDAO;
import edu.loyola.cs485.model.entity.Client;
import edu.loyola.cs485.view.MainFrame;

import java.sql.*;
import java.util.List;

public class Main {
    static String ConUrl = "jdbc:mysql://localhost";
    static String Port = "3306";
    static String Database = "person_db";
    static String Username = "root";
    static String Password = "root";

    static String url = ConUrl+":"+Port+"/"+Database+ "?user="+Username+"&password="+Password;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            String url = "jdbc:mysql://localhost:3306/person_db?user=root&password=root";
            Connection con = DriverManager.getConnection(url);
            System.out.println("Connected to the database");

            con.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

