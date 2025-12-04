package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.Animal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO extends AbstractDAO<Animal> {
    @Override
    public void create(Animal entity) throws SQLException {
        Connection con = getConnection(); // Always open a connection

        String sql = "INSERT INTO Animal(name_animal, dob_animal, breed_animal, color, species) VALUES (?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setString(1, entity.getName());
        pst.setDate(2, entity.getDob());
        pst.setString(3, entity.getBreed());
        pst.setString(4, entity.getColor());
        pst.setString(5, entity.getSpecies());
        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            entity.setId(rs.getInt(1));
        }

        con.close(); // Dont forget to close it
    }


    @Override
    public Animal read(int ID) throws SQLException {
        Animal animal = new Animal();
        Connection con = getConnection();

        String sql = "SELECT * FROM Animal WHERE id_animal = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, ID);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            animal.setId(rs.getInt("id_animal"));
            animal.setName(rs.getString("name_animal"));
            animal.setDob(rs.getDate("dob_animal"));
            animal.setBreed(rs.getString("breed"));
            animal.setColor(rs.getString("color"));
            animal.setSpecies(rs.getString("species"));

        }

        con.close();
        return animal;
    }

    @Override
    public void update(Animal entity) throws SQLException {
        Connection con = getConnection();

        String sql = "UPDATE Animal SET name_animal = ?, dob_animal = ?, breed_animal = ?, color = ?, species = ? " +
                "WHERE id_animal = ?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, entity.getName());
        pst.setDate(2, entity.getDob());
        pst.setString(3, entity.getBreed());
        pst.setString(4, entity.getColor());
        pst.setString(5, entity.getSpecies());
        pst.setInt(6, entity.getId());

        pst.executeUpdate();
        con.close();

    }

    @Override
    public void delete(int ID) throws SQLException {
        Connection con = getConnection();
        String sql = "DELETE FROM Animal WHERE id_animal = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, ID);
        pst.executeUpdate();

        con.close();
    }

    @Override
    public List<Animal> list() throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();

        Connection con = getConnection();
        String sql = "SELECT * FROM Animal ORDER BY name_animal";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Animal a = new Animal();

            a.setId(rs.getInt("id_animal"));
            a.setName(rs.getString("name_animal"));
            a.setDob(rs.getDate("dob_animal"));
            a.setBreed(rs.getString("breed"));
            a.setColor(rs.getString("color"));
            a.setSpecies(rs.getString("species"));

            animals.add(a);

        }
        return animals;
    }
}
