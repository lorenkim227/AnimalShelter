package edu.loyola.cs485.controller;

import edu.loyola.cs485.model.entity.Animal;
import edu.loyola.cs485.model.dao.AnimalDAO;
import java.util.List;
import java.text.SimpleDateFormat;


public class AnimalService {

    /* Create an animal via AnimalDAO
     * @param name
     * @param breed
     * @param color
     * @param species
     * @param dob
     * @return a new animal object to put as a new row in animal table
     */
    public Animal createAnimal(String name, String breed, String color, String species, String dob) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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


    /* Update an existing animal via AnimalDAO
     * @param name
     * @param breed
     * @param color
     * @param species
     * @param dob
     * @return a new animal object to replace existing row in animal table
     */
    public Animal updateAnimal(int id, String name, String breed, String color, String species, String dob) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

        // Call DAO to update the record in the database
        dao.update(animal);

        return animal;
    }

    /*
     * return all existing animal rows in database
     */
    public List<Animal> getAllAnimals() throws Exception {
        AnimalDAO dao = new AnimalDAO();
        return dao.list();
    }

    /*
    * delete an animal via dao
     */
    public void deleteAnimal(int id) throws Exception {
        AnimalDAO dao = new AnimalDAO();
        dao.delete(id);
    }
}
