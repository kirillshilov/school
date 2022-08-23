ALTER TABLE Student ADD CONSTRAINT age_constraint CHECK  (age >= 16);
ALTER TABLE student ADD CONSTRAINT name_unique UNIQUE ( name );
ALTER TABLE student ALTER COLUMN name SET not null;
ALTER TABLE faculty ADD CONSTRAINT unique_name_and_color UNIQUE (name, color);
ALTER TABLE student ALTER COLUMN age SET default 20;