-- Database creation from postgres role
CREATE DATABASE testdb
   WITH OWNER = postgres
      ENCODING = 'UTF8'
      TABLESPACE = pg_default      
      CONNECTION LIMIT = -1;
       
-- USE testdb;

-- Table creation from postgres role
CREATE TABLE employee (
   employee_id numeric(10) NOT NULL,
   employee_key varchar(40) NOT NULL,
   PRIMARY KEY (employee_id)
);

CREATE TABLE schedule (
   schedule_id numeric(10) NOT NULL,
   employee_id numeric(10) NOT NULL,
   startdatetime timestamp NOT NULL,
   enddatetime timestamp NOT NULL,
   PRIMARY KEY (schedule_id)
);

ALTER TABLE schedule ADD FOREIGN KEY (employee_id) REFERENCES employee (employee_id);

-- A new role
CREATE ROLE testdb LOGIN ENCRYPTED PASSWORD 'md524bced8e819475d9f6c7ebbab62accf1'   -- qwerty
  SUPERUSER NOINHERIT
   VALID UNTIL 'infinity';
UPDATE pg_authid SET rolcatupdate=false WHERE rolname='testdb';