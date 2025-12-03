package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.Animal;
import java.sql.*;
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
        return null;
    }

    @Override
    public void update(Animal entity) throws SQLException {
    }

    @Override
    public void delete(int ID) throws SQLException {
    }

    @Override
    public List<Animal> list() throws SQLException {
        return null;
    }
}
