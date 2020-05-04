-----------------------------------------
create tablespace ram_tabspace
    datafile 'ram_tabspace.dat'
    size 10M autoextend on;
create temporary tablespace ram_tabspace_temp
    tempfile 'ram_tabspace_temp.dat'
    size 5M autoextend on;
create user ram
    identified by ram
    default tablespace ram_tabspace
    temporary tablespace ram_tabspace_temp;
grant create session to ram;
grant create table to ram;
grant unlimited tablespace to ram;
GRANT ALL PRIVILEGES TO ram;
------------------------------------------------------------------------------------
--after executing above code click on connections then click on '+'
--give connection name:ram;user name: ram;password : ram
--then 'test' connection then click on 'connect'
--now create below 8 tables
------------------------------------------------------------------------------------

drop table room_entry;
drop table chiefcomplaint;
drop table patient;
drop table doctor;
drop table staff;
delete from rooms;
delete from room_entry;
delete from chiefcomplaint;
delete from doctor;
delete from staff;
delete from patient;
delete from hmsuser;
--1
create table rooms(
    roomno varchar2(15) primary key ,
    typeofroom varchar2(15) 
);
--2
create table room_entry(
    entryid number(20) primary key ,
    roomno references rooms(roomno),
    patientid  references patient(patientid),
    arrivaldate date
);

--3
CREATE table patient
(
    patientid VARCHAR2(20) primary key references hmsuser(userid)
);

--4
CREATE table hmsuser
(
    userid VARCHAR2(20) primary key,
    usertype varchar2(15),
    emailid varchar2(30),
    imageurl varchar2(40),
    uname VARCHAR2(25) ,
    gender VARCHAR2(9),
    address VARCHAR(30),
    phno varchar2(20),
    birthdate date
);

--5
CREATE table staff
(
    staffid VARCHAR2(20) primary key references hmsuser(userid),
    job varchar2(20),
    salary number(12),
    dateofjoin date,
    arrivaltime date,
    departtime date
);

--6
CREATE table doctor
(
    doctorid VARCHAR2(20) primary key references staff(staffid),
    degrees varchar2(25),
    typeofdoctor varchar2(20)
);

--7
create table chiefcomplaint(
    caseid number(20) primary key,
    patientid VARCHAR2(20) REFERENCES patient(patientid),
    chief_complaint varchar2(20),
    description  varchar2(40),
    doctorid varchar2(20) REFERENCES doctor,
    status varchar2(20),
    prescription varchar2(40),
    creationdate date
);

--8
CREATE table login
(
    userid VARCHAR2(20) primary key references hmsuser(userid),
    password varchar2(25)
);