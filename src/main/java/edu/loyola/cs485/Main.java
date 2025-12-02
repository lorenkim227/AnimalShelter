package edu.loyola.cs485;

//import edu.loyola.cs485.model.dao.ClientDAO;
//import edu.loyola.cs485.model.entity.Client;
//import edu.loyola.cs485.view.MainFrame;

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

    public static void secureInsertPerson() throws SQLException{
        Connection con = DriverManager.getConnection(url);

        // all of our entities use FK person, so we need to add one person item
        String sql = "INSERT INTO person(name, email, phone, bank_info) VALUES (?,?,?,?);";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, "Jane Doe");
        pst.setString(2,"janedoe@shelter.com");
        pst.setString(3,"123456789");
        pst.setString(4,"000000000"); // represents a routing num
        pst.executeUpdate();

        con.close();
    }

    public static void secureInsertAdoption() throws SQLException{
        Connection con = DriverManager.getConnection(url);

        // all of our entities use FK person, so we need to add one person item
        String sql = "INSERT INTO adoption(id_person, adoption_date, adoption_fee, adoption_adopter_fk) VALUES (?,?,?,?);";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, "1");
        pst.setString(2,"12/02/2025");
        pst.setString(3,"100");
        pst.setString(4,"1"); // represents a routing num
        pst.executeUpdate();

        con.close();
    }
}

