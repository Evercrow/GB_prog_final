#### Задание 7:
*В подключенном MySQL репозитории создать базу данных "Друзья человека"*

Сперва войдем в оболочку mysql. По умолчанию для root создается юзер root без пароля(но понадобится пароль su)

    sudo mysql -u root -p
    Enter password:
    Welcome to the MySQL monitor.  Commands end with ; or \g.
    ...

Создаем новую базу данных(дропаем, если идет перезапуск скрипта):

    DROP DATABASE IF EXISTS human_friends;
    CREATE DATABASE human_friends;
    show databases;
    +--------------------+
    | Database           |
    +--------------------+
    | human_friends      |
    | information_schema |
    | mysql              |
    | mytest             |
    | performance_schema |
    | phpmyadmin         |
    | sys                |
    +--------------------+
7 rows in set (0,01 sec)

#### Задание 8:
*Создать таблицы с иерархией из диаграммы в БД*

Будем спускаться по уровням, создавая дополнительные поля-связи id внешних ключей. На каждом уровне создаются новое поле(сначала Class_id, потом Species_id, наконец, id для конкретных животных) . Недостатки - уникальные ключи нужно обязательно делать составными при создании объединенных витрин\таблиц
Достоинства - расширять такую базу довольно просто новыми таблицами\классами, просто добавляя строчку на уровень выше и получая нужный id родителя.


Используем **SET FOREIGN_KEY_CHECKS = 0;**, чтобы отключить защиту от  каскадных изменений по внешним ключам при удалении строк, после этого TRUNCATE начинает работать(и мы не получим дубликатов записей при перезапуске скрипта) 

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

#### Задание 9:
Заполнить низкоуровневые таблицы именами(животных), командами
которые они выполняют и датами рождения

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
    
    SELECT * FROM camels;
    +----+------------------+------------+----------------------+------------+
    | Id | Name             | Birthday   | Commands             | Species_id |
    +----+------------------+------------+----------------------+------------+
    |  1 | Горбатый         | 2022-04-10 | вернись              |          3 |
    |  2 | Самец            | 2019-03-12 | остановись           |          3 |
    |  3 | Сифон            | 2015-07-12 | повернись            |          3 |
    |  4 | Борода           | 2022-12-10 | ложись               |          3 |
    +----+------------------+------------+----------------------+------------+

    SELECT * FROM burden_animals;
    +----+------------------+----------+
    | Id | Species          | Class_id |
    +----+------------------+----------+
    |  1 | Лошади           |        1 |
    |  2 | Ослы             |        1 |
    |  3 | Верблюды         |        1 |
    +----+------------------+----------+

    SELECT * FROM animals;
    +----+------------------+
    | Id | Class_name       |
    +----+------------------+
    |  1 | вьючные          |
    |  2 | домашние         |
    +----+------------------+




#### Задание 10:
Удалить из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.

#### Решение:
Удалить можно таблицу через **DROP camels**,
быстро очистить через **TRUNCATE camels**,
или использовать оператор **DELETE для удаления строк без указания фильтра, тогда тоже удалятся все строчки:

    DELETE FROM camels;

    

Собираем строчки в одну таблицу через UNION .
Чтобы действительно создать новую таблицу, можно обернуть запрос в  **CREATE TABLE** (mySQL) или **SELECT * INTO**(в других sql). Использовать вместо этого лучше витрины (views), так как сохранять специфичные выборки в базу данных - это моветон.


    CREATE TABLE horses_with_donkeys SELECT * FROM (

	SELECT CONCAT(id,Species_id) AS a_id, Name, Birthday, Commands,Species_id
	FROM horses
	UNION
	SELECT CONCAT(id,Species_id)  AS a_id,Name, Birthday, Commands,Species_id
	FROM donkeys) task10_select;

    SELECT * FROM horses_with_donkeys;
    +------+--------------------+------------+----------------------------------------+------------+
    | a_id | Name               | Birthday   | Commands                               | Species_id |
    +------+--------------------+------------+----------------------------------------+------------+
    | 11   | Гром               | 2020-01-12 | бегом, шагом                           |          1 |
    | 21   | Закат              | 2017-03-12 | бегом, шагом, хоп                      |          1 |
    | 31   | Байкал             | 2016-07-12 | бегом, шагом, хоп, брр                 |          1 |
    | 41   | Молния             | 2020-11-10 | бегом, шагом, хоп                      |          1 |
    | 12   | Ушастый            | 2019-04-10 |                                        |          2 |
    | 22   | Второй             | 2020-03-12 | Но                                     |          2 |
    | 32   | Хаджа              | 2021-07-12 | Тпру                                   |          2 |
    | 42   | Четвертый          | 2022-12-10 | Стоп                                   |          2 |
    +------+--------------------+------------+----------------------------------------+------------+


#### Задание 11:
Создать новую таблицу “молодые животные” в которую попадут все
животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
до месяца подсчитать возраст животных в новой таблице


    DROP VIEW young_animal;
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
    select * from young_animals;
    +------+----------------+------------+---------------+----------------------------------------------+--------------+
    | a_id | Name           | Birthday   | Age_in_months | Commands                                     | species      |
    +------+----------------+------------+---------------+----------------------------------------------+--------------+
    | 41   | Молния         | 2020-11-10 |            34 | бегом, шагом, хоп                            | Лошади       |
    | 32   | Хаджа          | 2021-07-12 |            26 | Тпру                                         | Ослы         |
    | 22   | Полкан         | 2021-06-12 |            27 | сидеть, лежать, лапу                         | Собаки       |
    | 42   | Рекс           | 2021-05-10 |            28 | сидеть, лежать, фу, место                    | Собаки       |
    | 13   | Малой          | 2020-10-12 |            35 |                                              | Хомяки       |
    | 23   | Бит            | 2021-03-12 |            30 | в клетку                                     | Хомяки       |
    | 33   | Ниндзя         | 2022-07-11 |            14 |                                              | Хомяки       |
    | 43   | Шустрый        | 2022-05-10 |            16 | кушать                                       | Хомяки       |
    +------+----------------+------------+---------------+----------------------------------------------+--------------+

нужен дополнительный уровень обертки(псевдоним t), чтобы фильтр накладывался ПОСЛЕ высчитывания столбца Age_in_months


#### Задание 12:
Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
прошлую принадлежность к старым таблицам.

#### Решение
Принцип объединения - классы одного уровня объединяются через UNION, родительские классы поэтапно присоединяются через JOIN по ключам. Так как поля Id везде называются одинаково, необходимо при таком вложенном объединении давать псевдоним (вместо простого SELECT *).
Вид со всем полями есть в представлении f, но для более читаемого вида все колонки с id были собраны конкатенацией в одно поле unique_id в конечном виде.

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

**[Скрипт](animals_db.sql) с командами SQL для быстрой проверки**