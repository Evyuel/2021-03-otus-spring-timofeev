create table Author(ID int primary key,
                    Fullname varchar(255));
CREATE SEQUENCE Author_SQ
  START WITH 7
  INCREMENT BY 1;
create table Genre(ID int primary key,
                   Name varchar(255));
CREATE SEQUENCE Genre_SQ
  START WITH 4
  INCREMENT BY 1;
create table Book(ID int primary key,
                  Name varchar(255),
                  GenreID int,
                  AuthorID int,
                  foreign key (GenreID) references Genre(ID),
                  foreign key (AuthorID) references Author(ID));
CREATE SEQUENCE Book_SQ
  START WITH 7
  INCREMENT BY 1;