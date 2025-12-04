package edu.loyola.cs485;

import edu.loyola.cs485.model.dao.AnimalDAO;
import edu.loyola.cs485.model.entity.Animal;
import edu.loyola.cs485.view.MainFrame;

import java.sql.*;
import java.util.List;

public class Main {
    static String ConUrl = "jdbc:mysql://localhost";
    static String Port = "3306";
    static String Database = "animal_db";
    static String Username = "groupThree";
    static String Password = "Animal123";

    static String url = ConUrl+":"+Port+"/"+Database+ "?user="+Username+"&password="+Password;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            String url = "jdbc:mysql://localhost:3306/animal_db?user=groupThree&password=Animal123";
            Connection con = DriverManager.getConnection(url);
            System.out.println("Connected to the database");

            //secureInsertAnimal();
            con.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void secureInsertAnimal() throws SQLException{
        Connection con = DriverManager.getConnection(url);

        // all of our entities use FK person, so we need to add one person item
        String sql = "INSERT INTO animal(name_animal, dob_animal, breed_animal, color, species) VALUES (?,?,?,?,?);";

        PreparedStatement pst = con.prepareStatement(sql);
        // my personal dog :)
        pst.setString(1, "Peyote");
        pst.setString(2,"03/21/2019");
        pst.setString(3,"German Shepherd");
        pst.setString(4,"Bi-color");
        pst.setString(5,"Canine");
        pst.executeUpdate();

        con.close();

    }

    //secureInsertAnimal();
}

