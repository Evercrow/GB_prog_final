DROP DATABASE IF EXISTS human_friends;
CREATE DATABASE human_friends;
show databases;
USE human_friends;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE IF NOT EXISTS animals
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Class_name VARCHAR(20) NOT NULL
);
TRUNCATE animals;
INSERT INTO animals (Class_name)
VALUES  ('вьючные'),
		('домашние');
        
CREATE TABLE IF NOT EXISTS burden_animals
(
	  Id INT AUTO_INCREMENT PRIMARY KEY,
    Species VARCHAR (20) NOT NULL,
    Class_id INT NOT NULL,
    FOREIGN KEY (Class_id) REFERENCES animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
TRUNCATE burden_animals;
INSERT INTO burden_animals (Species, Class_id)
VALUES ('Лошади', 1),
		('Ослы', 1),  
		('Верблюды', 1); 
    
CREATE TABLE IF NOT EXISTS home_animals
(
	  Id INT AUTO_INCREMENT PRIMARY KEY,
    Species VARCHAR (20) NOT NULL,
    Class_id INT NOT NULL,
    FOREIGN KEY (Class_id) REFERENCES animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
TRUNCATE home_animals;
INSERT INTO home_animals (Species, Class_id)
VALUES ('Кошки', 2),
	('Собаки', 2),  
	('Хомяки', 2); 

CREATE TABLE IF NOT EXISTS cats 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(200) DEFAULT "",
    Species_id int NOT NULL,
    Foreign KEY (Species_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
TRUNCATE cats;
INSERT INTO cats (Name, Birthday, Commands, Species_id)
VALUES ('Мурзик', '2011-01-01', 'кис-кис', 1),
	('Черныш', '2016-01-01', "домой!", 1),  
	('Балу', '2017-01-01', "иди спать", 1); 

CREATE TABLE IF NOT EXISTS dogs 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(200) DEFAULT "",
    Species_id int NOT NULL,
    Foreign KEY (Species_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
TRUNCATE dogs;
INSERT INTO dogs (Name, Birthday, Commands, Species_id)
VALUES ('Барон', '2020-01-01', 'ко мне, лежать, лапу, голос', 2),
	('Полкан', '2021-06-12', "сидеть, лежать, лапу", 2),  
	('Шарик', '2018-05-01', "сидеть, лежать, лапу, след, фас", 2), 
	('Рекс', '2021-05-10', "сидеть, лежать, фу, место", 2);

CREATE TABLE IF NOT EXISTS hamsters 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(200) ,
    Species_id int NOT NULL DEFAULT 3,
    Foreign KEY (Species_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
TRUNCATE hamsters;
INSERT INTO hamsters (Name, Birthday, Commands)
VALUES ('Малой', '2020-10-12', ''),
	('Бит', '2021-03-12', "в клетку"),  
	('Ниндзя', '2022-07-11',""), 
	('Шустрый', '2022-05-10',"кушать");

CREATE TABLE IF NOT EXISTS horses 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(200) ,
    Species_id int NOT NULL DEFAULT 1,
    Foreign KEY (Species_id) REFERENCES burden_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
TRUNCATE horses;
INSERT INTO horses (Name, Birthday, Commands)
VALUES ('Гром', '2020-01-12', 'бегом, шагом'),
	('Закат', '2017-03-12', "бегом, шагом, хоп"),  
	('Байкал', '2016-07-12', "бегом, шагом, хоп, брр"), 
	('Молния', '2020-11-10', "бегом, шагом, хоп");

CREATE TABLE IF NOT EXISTS donkeys 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(200) ,
    Species_id int NOT NULL DEFAULT 2,
    Foreign KEY (Species_id) REFERENCES burden_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
TRUNCATE donkeys;
INSERT INTO donkeys (Name, Birthday, Commands)
VALUES ('Ушастый', '2019-04-10', ""),
	('Второй', '2020-03-12', "Но"),  
	('Хаджа', '2021-07-12', "Тпру"), 
	('Четвертый', '2022-12-10', "Стоп");

CREATE TABLE IF NOT EXISTS camels 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(200),
    Species_id int NOT NULL DEFAULT 3,
    Foreign KEY (Species_id) REFERENCES burden_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
TRUNCATE camels;
INSERT INTO camels (Name, Birthday, Commands)
VALUES ('Горбатый', '2022-04-10', 'вернись'),
	('Самец', '2019-03-12', "остановись"),  
	('Сифон', '2015-07-12', "повернись"), 
	('Борода', '2022-12-10', "ложись");

DELETE FROM camels;

CREATE TABLE horses_with_donkeys SELECT * FROM (

	SELECT CONCAT(id,Species_id) AS a_id, Name, Birthday, Commands,Species_id
	FROM horses
	UNION
	SELECT CONCAT(id,Species_id)  AS a_id,Name, Birthday, Commands,Species_id
	FROM donkeys) task10_select;
    
SELECT * FROM horses_with_donkeys;

CREATE OR REPLACE  VIEW  young_animals AS
SELECT * FROM (
	SELECT CONCAT(id,Species_id) AS a_id,`Name`, Birthday, TIMESTAMPDIFF(MONTH, a.Birthday, SYSDATE()) AS Age_in_months, Commands,species FROM
	(SELECT *, 'Лошади' as species FROM horses
	UNION SELECT *, 'Ослы' AS species FROM donkeys
    UNION SELECT *, 'Верблюды' AS species FROM camels
	UNION SELECT *, 'Собаки' AS species FROM dogs
	UNION SELECT *, 'Кошки' AS species FROM cats
	UNION SELECT *, 'Хомяки' AS species FROM hamsters) a
    ) t
    WHERE t.Age_in_months BETWEEN 12 AND 36;
# нужен дополнительный уровень обертки, чтобы фильтр накладывался ПОСЛЕ высчитывания столбца Age_in_months    
select * from young_animals;

CREATE OR REPLACE  VIEW  full_house AS
SELECT CONCAT(a_id,Species_id,Class_id) as unique_id,`Name`,Birthday,Commands,Species,Class_name,'Друзья человека' as root_class FROM ( 
	SELECT *  FROM (
			SELECT low.Id as a_id,`Name`,Birthday,Commands,Species_id,Species,Class_Id  FROM (
				SELECT * FROM horses
				UNION SELECT * FROM donkeys
				UNION SELECT *  FROM camels
				UNION SELECT * FROM dogs
				UNION SELECT * FROM cats
				UNION SELECT * FROM hamsters
				) low
			JOIN (
				SELECT * FROM burden_animals 
                UNION SELECT * FROM home_animals
                )mid 
			ON mid.Id = low.Species_id 
            ) as mid_to_low
	JOIN animals a ON a.Id = mid_to_low.Class_id
	) f;
        
SELECT * from full_house;

		

