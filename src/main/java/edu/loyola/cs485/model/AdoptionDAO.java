package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.Adoption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdoptionDAO extends AbstractDAO<Adoption> {
    @Override
    public void create(Adoption entity) throws SQLException {

    }


    @Override
    public Adoption read(int ID) throws SQLException {
        return null;
    }

    @Override
    public void update(Adoption entity) throws SQLException {
    }

    @Override
    public void delete(int ID) throws SQLException {
    }

    @Override
    public List<Adoption> list() throws SQLException {
        return null;
    }
}
