package edu.loyola.cs485.model.entity;
import java.sql.Date;

//NOTE!!!: For ease, we are assuming that none of the animals are adopted. Therefore id_adoption will ALWAYS be null.

/*
* Original for animal
	id_animal int not null auto_increment,
	id_adoption int,
	name_animal varchar(50) not null,
	dob_animal datetime not null,
	breed_animal varchar(20) not null,
	color varchar(20) not null,
	species varchar(20) not null,
	constraint animal_pk primary key(id_animal),
	constraint animal_adoption_fk foreign key(id_adoption) references adoption(id_adoption)

* */
public class Animal {
    private Integer id_animal = null;
    private Integer id_adoption = null;
    private String name_animal;
    private String breed_animal;
    private String color;
    private String species;
    private Date dob_animal;

    public Integer getId() {
        return id_animal;
    }

    public void setId(Integer id_animal) {
        this.id_animal = id_animal;
    }

    public Integer getId_adoption() {
        return id_adoption;
    }

    public void setId_adoption(Integer id_adoption) {
        this.id_adoption = id_adoption;
    }

    public String getName() {
        return name_animal;
    }

    public void setName(String name_animal) {
        this.name_animal = name_animal;
    }

    public String getBreed() {
        return breed_animal;
    }

    public void setBreed(String breed_animal) {
        this.breed_animal = breed_animal;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Date getDob() {
        return dob_animal;
    }

    public void setDob(Date dob_animal) {
        this.dob_animal = dob_animal;
    }



    @Override
    public String toString(){
        return getId().toString()+": "+getName()+" "+getBreed()+" "+getColor()+" "+getSpecies()+" "+getDob();
    }
}
