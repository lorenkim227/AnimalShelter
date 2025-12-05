create table animal (
	id_animal int not null auto_increment,
	-- id_adoption int,
	name_animal varchar(50) not null,
	dob_animal datetime not null,
	breed_animal varchar(20) not null,
	color varchar(20) not null,
	species varchar(20) not null,
	constraint animal_pk primary key(id_animal)
	-- constraint animal_adoption_fk foreign key(id_adoption) references adoption(id_adoption)
);

-- removed the 2 lines since we aren't making an adoption table and it causes an error in MySql