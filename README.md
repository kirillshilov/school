# school

Учебное web rest приложение.
Приложение Занимается учетом студентов и факультетов и аватаров студентов. Таблицы связаны.
Данные хранятся в базе данных PostgreSQL, для сохранения данных нужно создать базу данных с параметрами.

spring.datasource.url = jdbc:postgresql://localhost:5432/hogwarts
spring.datasource.username = student
spring.datasource.password = chocolatefrog

Таблицы создаются автоматически при запуске.

Доступ к приложению Через адрес http://localhost:8080/swagger-ui/index.html#/

Используемые технологии:

Spring 
Hibernate
PostgreSQL
SQL запросы и методы Quary
Liquibase
Maven
Swager
