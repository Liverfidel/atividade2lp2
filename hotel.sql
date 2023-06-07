create database hotel;
use hotel;

create table cliente(
  id integer not null auto_increment,
  nome varchar(50) not null, 
  email varchar(50) not null,
  senha char(64) not null, 
  primary key(id)
);

create table quarto(
  numQuarto integer not null auto_increment,
  andar varchar(50) not null, 
  tipo varchar(50) null,
  cliente_id integer not null,
  primary key(numQuarto),
  foreign key(cliente_id) references cliente(id)
);