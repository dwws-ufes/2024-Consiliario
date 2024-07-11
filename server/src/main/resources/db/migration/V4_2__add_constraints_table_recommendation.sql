ALTER TABLE consiliario.Recommendation
    ADD CONSTRAINT fk_sender
        FOREIGN KEY (sender) REFERENCES consiliario.Teacher (id)
            ON DELETE CASCADE;

ALTER TABLE consiliario.Recommendation
    ADD CONSTRAINT fk_receiver
        FOREIGN KEY (receiver) REFERENCES consiliario.Student (id)
            ON DELETE CASCADE;