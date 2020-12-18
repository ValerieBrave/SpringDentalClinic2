select * from Doctor;
select * from Patient;
select * from Visit;

drop table Visit;
drop  table Doctor;

create table Visit
(
	visitID integer primary key identity(1,1),
	doctorID integer foreign key references Doctor(doctorID),
	cardID integer foreign key references Patient(cardID),
	date datetime,
	complains varchar(500),
	diagnosis varchar(500),
	treatment varchar(500)
);

create table Doctor
(
	doctorID integer primary key identity(1,1),
	role varchar(20) foreign key references Role(role),
	name varchar(255),
	speciality varchar(255),
	login varchar(50),
	password varchar(50),
	email varchar(100)
);
drop table Role;
create table Role (role varchar(20) primary key);