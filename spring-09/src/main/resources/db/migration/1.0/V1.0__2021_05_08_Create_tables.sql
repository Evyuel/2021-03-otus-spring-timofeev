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

create table Book(ID int primary key,
                  Name varchar(255),
                  GenreID int,
                  foreign key (GenreID) references Genre(ID));
CREATE SEQUENCE Book_SQ
  START WITH 9
  INCREMENT BY 1;

create table BookAuthorLink(LinkID int primary key,
                            BookID int,
                            foreign key (BookID) references Book(ID),
                            AuthorID int,
                            foreign key (AuthorID) references Author(ID));
CREATE SEQUENCE BookAuthorLink_SQ
  START WITH 11
  INCREMENT BY 1;