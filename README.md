

# Steps to run

## Setup databases
CREATE DATABASE APIDevelopSpringBoot;

use APIDevelopSpringBoot;

CREATE TABLE Storage2(book_name varchar(50),id varchar(50),

isbn varchar(50),

aisle varchar(50),

author varchar(50),PRIMARY KEY (id));

INSERT INTO Storage2(book_name,id,isbn,aisle,author) values("Microservices","hrtge43","hrtge","43","Shetty");
INSERT INTO Storage2(book_name,id,isbn,aisle,author) values("Selenium","khuys21","khuys","21","Shetty");
INSERT INTO Storage2(book_name,id,isbn,aisle,author) values("Appium","ttefs36","ttefs","36","Shetty");

select * from Storage2;


## Second setup
CREATE DATABASE Courses;

use Courses;

CREATE TABLE Storage2(course_name varchar(50),id varchar(50),

price int,

category varchar(50),

PRIMARY KEY (course_name));


INSERT INTO Storage2(course_name,id,price,category) values("Microservices testing","2",23,"api");
INSERT INTO Storage2(course_name,id,price,category) values("Selenium","3",66,"web");
INSERT INTO Storage2(course_name,id,price,category) values("Appium","12",13,"mobile");

## Update properties files with the appropriate information