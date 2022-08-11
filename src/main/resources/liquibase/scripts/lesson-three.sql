-- liquibase formatted sql

-- changeSet shilovk : 1
CREATE INDEX student_name ON student (name)
-- changeSet shilovk : 2
CREATE INDEX faculty_name_color ON faculty (name, color)

