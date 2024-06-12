CREATE TABLE events
(
    id bigserial primary key,
    reg_start   TIMESTAMP,
    reg_end     TIMESTAMP,
    persons     INT,
    chois_start TIMESTAMP,
    chois_end   TIMESTAMP
);


ALTER TABLE users
    ADD COLUMN events_id INT,
    ADD FOREIGN KEY (events_id) REFERENCES events(id);

