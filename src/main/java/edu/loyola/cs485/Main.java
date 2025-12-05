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
        MainFrame frame = new MainFrame(); // Instantiates the Window
        frame.setVisible(true); // Activates it (and turn it visible)

    }

}

