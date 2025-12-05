package edu.loyola.cs485.controller;

import edu.loyola.cs485.model.entity.Animal;
import edu.loyola.cs485.model.dao.AnimalDAO;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 * For an MVC architecture, we need another class to separate the Interface (View) from the DAOs (Model)
 * Even if it seems redudant to you, it is a good practice (make a habit of it).
 */
public class AnimalService {

    public Animal createAnimal(String name, String breed, String color, String species, String dob) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.sql.Date tempDob = new java.sql.Date(sdf.parse(dob).getTime());

        Animal animal = new Animal();
        animal.setName(name);
        animal.setBreed(breed);
        animal.setDob(tempDob);
        animal.setColor(color);
        animal.setSpecies(species);

        AnimalDAO dao = new AnimalDAO();
        dao.create(animal);

        return animal;
       //return null;
    }

    public Animal updateAnimal(int id, String name, String breed, String color, String species, String dob) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.sql.Date newDob = new java.sql.Date(sdf.parse(dob).getTime());

        // fetch existing Animal object with updated information
        AnimalDAO dao = new AnimalDAO();
        Animal animal = dao.read(id);

        if (animal == null) {
            throw new Exception("Animal with id " + id + " does not exist");
        }
        animal.setName(name);
        animal.setBreed(breed);
        animal.setDob(newDob);
        animal.setColor(color);
        animal.setSpecies(species);

        dao.update(animal);

        return animal;
    }
/*
    public Animal deleteAnimal() throws Exception {
        return null;
    }
*/
    public List<Animal> getAllAnimals() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        return dao.list();
    }

    public void deleteAnimal(int id) throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.delete(id);
    }
}
