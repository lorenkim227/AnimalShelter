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

    public Integer getID() {
        return id_animal;
    }

    public void setID(Integer ID) {
        this.id_animal = ID;
    }

    public String getName() {
        return name_animal;
    }

    public void setName(String name) {
        this.name_animal = name;
    }


    public Date getDob() {
        return dob_animal;
    }

    public void setDob(Date dob) {
        this.dob_animal = dob;
    }

    @Override
    public String toString(){
        return getID().toString()+": "+getName()+" <"+getEmail()+">";
    }
}
