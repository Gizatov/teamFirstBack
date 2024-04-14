ALTER TABLE students_voice
    DROP CONSTRAINT students_voice_student_id_fkey,
    ADD CONSTRAINT students_voice_student_id_fkey
        FOREIGN KEY (student_id)
            REFERENCES users(id)
            ON DELETE CASCADE;