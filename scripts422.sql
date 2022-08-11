CREATE TABLE car(
    id SERIAL PRIMARY KEY ,
    brand VARCHAR(50),
    model VARCHAR (50),
    price SERIAL
);

CREATE TABLE human (
                       id SERIAL,
                        name VARCHAR (50) PRIMARY KEY,
                       driverLicense boolean,
                       age SMALLSERIAL,
                       car_id INT REFERENCES car (id)
);