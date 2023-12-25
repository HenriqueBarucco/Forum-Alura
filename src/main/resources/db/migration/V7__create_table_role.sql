CREATE TABLE role(
     id bigint not null auto_increment,
     nome varchar(50) not null,
     primary key(id)
);

INSERT INTO role (id, nome) VALUES (1, 'LEITURA_ESCRITA');