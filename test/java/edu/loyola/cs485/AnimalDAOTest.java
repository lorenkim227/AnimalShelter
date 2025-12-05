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

    @AfterAll
    public static void cleanup() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();
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
    public void CreateAnimal() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        //Loren's dog :)
        Animal animal = new Animal();
        animal.setName("Peyote");
        animal.setDob(Date.valueOf("2019-03-21"));
        animal.setBreed("German Shepherd");
        animal.setColor("Bi-color");
        animal.setSpecies("Canine");

        dao.create(animal);

        assertAll(
                () -> assertNotNull(animal.getId())
        );
    }

    @Test
    public void ReadAnimal() throws Exception {
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
    public void ReadAnimalDoesNotExist() throws Exception {
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
    public void UpdateAnimal() throws Exception {
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

        dao.update(animal);

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
    public void DeleteAnimal() throws Exception {
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
    public void EmptyListAnimals() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        List<Animal> list = dao.list();

        assertAll(
                () -> assertEquals(0, list.size())
        );
    }

    @Test
    public void ListMultipleAnimalsInABCOrder() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        Animal a1 = new Animal();
        a1.setName("Carol");
        a1.setDob(Date.valueOf("2019-03-21"));
        a1.setBreed("German Shepherd");
        a1.setColor("Bi-color");
        a1.setSpecies("Canine");
        dao.create(a1);

        Animal a2 = new Animal();
        a2.setName("Alpha");
        a2.setDob(Date.valueOf("2019-01-01"));
        a2.setBreed("Breed2");
        a2.setColor("Brown");
        a2.setSpecies("Horse");
        dao.create(a2);

        Animal a3 = new Animal();
        a3.setName("Beta");
        a3.setDob(Date.valueOf("2025-08-01"));
        a3.setBreed("Tabby");
        a3.setColor("Gray");
        a3.setSpecies("Feline");
        dao.create(a3);

        List<Animal> list = dao.list();

        assertEquals(3, list.size());
        assertEquals("Alpha", list.get(0).getName());
        assertEquals("Beta", list.get(1).getName());
        assertEquals("Carol", list.get(2).getName());
    }

    @Test
    public void PartialUpdate() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        Animal animal = new Animal();
        animal.setName("OldName");
        animal.setDob(Date.valueOf("2020-01-01"));
        animal.setBreed("OldBreed");
        animal.setColor("Black");
        animal.setSpecies("Canine");
        dao.create(animal);

        // Only change name and color
        animal.setName("NewName");
        animal.setColor("White");
        dao.update(animal);

        Animal found = dao.read(animal.getId());

        //these are new
        assertEquals("NewName", found.getName());
        assertEquals("White", found.getColor());

        // These are old
        assertEquals("OldBreed", found.getBreed());
        assertEquals(Date.valueOf("2020-01-01"), found.getDob());
        assertEquals("Canine", found.getSpecies());
    }

    @Test
    public void ReadReturnsEmptyAnimalWhenNotFound() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        Animal found = dao.read(55555);

        assertNull(found.getId());
        assertNull(found.getName());
        assertNull(found.getDob());
        assertNull(found.getBreed());
        assertNull(found.getColor());
        assertNull(found.getSpecies());
    }

    @Test
    public void DeleteTwiceDoesNotFail() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.setTestDatabase();

        Animal animal = new Animal();
        animal.setName("ToDelete");
        animal.setDob(Date.valueOf("2020-10-10"));
        animal.setBreed("Breed");
        animal.setColor("Brown");
        animal.setSpecies("Canine");
        dao.create(animal);

        dao.delete(animal.getId()); // First delete
        dao.delete(animal.getId()); // Second delete do not throw.

        Animal found = dao.read(animal.getId());
        assertNull(found.getName());
    }

}
