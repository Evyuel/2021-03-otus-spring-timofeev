create table Author(ID int primary key,
                    Fullname varchar(255));
CREATE SEQUENCE Author_SQ
  START WITH 11
  INCREMENT BY 1;
create table Genre(ID int primary key,
                   Name varchar(255));
CREATE SEQUENCE Genre_SQ
  START WITH 4
  INCREMENT BY 1;