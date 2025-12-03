package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.Animal;
import java.sql.*;
import java.util.List;

public class AnimalDAO extends AbstractDAO<Animal> {
    @Override
    public void create(Animal entity) throws SQLException {

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
