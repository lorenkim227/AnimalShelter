package edu.loyola.cs485;

import edu.loyola.cs485.model.dao.AnimalDAO;
import edu.loyola.cs485.model.entity.Animal;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.List;


public class AnimalDAOTest {

    @BeforeEach
    public void resetDatabase() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        // Clear table
        Connection con = dao.getConnection();
        con.prepareStatement("DELETE FROM Animal").executeUpdate();
        con.close();
    }


    @Test
    public void testFake() {
        assertAll(
                () -> assertEquals(1, 1)
        );
    }

    @Test
    public void testCreateAnimal() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        Animal animal = new Animal();
        animal.setName("Peyote");
        animal.setDob(Date.valueOf("2019-03-21"));
        animal.setBreed("German Shepherd");
        animal.setColor("Bi-color");
        animal.setSpecies("Canine");

        dao.create(animal);   // Method under test

        // cleanup
        dao.delete(animal.getId());

        assertAll(
                () -> assertNotNull(animal.getId())
        );
    }

    @Test
    public void testReadAnimal() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        // Insert test entity
        // My cat :)
        Animal animal = new Animal();
        animal.setName("Ming ming");
        animal.setDob(Date.valueOf("2025-08-01"));
        animal.setBreed("Tabby");
        animal.setColor("Gray");
        animal.setSpecies("Feline");

        dao.create(animal);

        // Method under test
        Animal found = dao.read(animal.getId());

        // cleanup
        dao.delete(animal.getId());

        assertAll(
                () -> assertEquals(animal.getId(), found.getId()),
                () -> assertEquals(animal.getName(), found.getName()),
                () -> assertEquals(animal.getDob(), found.getDob()),
                () -> assertEquals(animal.getBreed(), found.getBreed()),
                () -> assertEquals(animal.getColor(), found.getColor()),
                () -> assertEquals(animal.getSpecies(), found.getSpecies())
        );
    }

    @Test
    public void testReadAnimalDoesNotExist() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        Animal found = dao.read(9999);

        // default animal is null...because it doesn't exist
        assertAll(
                () -> assertNull(found.getId()),
                () -> assertNull(found.getName())
        );
    }

    @Test
    public void testUpdateAnimal() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        // Insert test data
        Animal animal = new Animal();
        animal.setName("Before Update");
        animal.setDob(Date.valueOf("2020-01-10"));
        animal.setBreed("Old Breed");
        animal.setColor("Black");
        animal.setSpecies("Canine");
        dao.create(animal);

        // Modify fields
        animal.setName("After Update");
        animal.setDob(Date.valueOf("2022-05-05"));
        animal.setBreed("New Breed");
        animal.setColor("White");
        animal.setSpecies("Feline");

        dao.update(animal);   // method under test

        Animal updated = dao.read(animal.getId());

        // cleanup
        dao.delete(animal.getId());

        assertAll(
                () -> assertEquals("After Update", updated.getName()),
                () -> assertEquals(Date.valueOf("2022-05-05"), updated.getDob()),
                () -> assertEquals("New Breed", updated.getBreed()),
                () -> assertEquals("White", updated.getColor()),
                () -> assertEquals("Feline", updated.getSpecies())
        );
    }

    @Test
    public void testDeleteAnimal() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        Animal animal = new Animal();
        animal.setName("Delete Me");
        animal.setDob(Date.valueOf("2018-03-20"));
        animal.setBreed("Bulldog");
        animal.setColor("White");
        animal.setSpecies("Canine");
        dao.create(animal);

        dao.delete(animal.getId());   // method under test
        Animal found = dao.read(animal.getId());

        assertAll(
                () -> assertNull(found.getName())
        );
    }

    @Test
    public void testListAnimals() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        List<Animal> list = dao.list();

        assertAll(
                () -> assertEquals(0, list.size())
        );
    }
}
