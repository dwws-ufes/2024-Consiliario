ALTER TABLE consiliario.Meeting
    ADD CONSTRAINT fk_teacher
        FOREIGN KEY (teacher) REFERENCES consiliario.Teacher (id)
            ON DELETE CASCADE;

ALTER TABLE consiliario.Meeting
    ADD CONSTRAINT fk_student
        FOREIGN KEY (student) REFERENCES consiliario.Student (id)
            ON DELETE CASCADE;