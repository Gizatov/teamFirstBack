create table public.roles
(
    id bigserial
        primary key,
    role_name varchar(255)
);



INSERT INTO public.roles (id, role_name) VALUES (1, 'admin');
INSERT INTO public.roles (id, role_name) VALUES (2, 'candidate');
INSERT INTO public.roles (id, role_name) VALUES (3, 'student');



CREATE TABLE public.users
(
    id           bigserial primary key,
    course       integer not null,
    email        varchar(255),
    faculty      varchar(255),
    gender       varchar(255),
    is_activated boolean,
    last_name    varchar(255),
    name         varchar(255),
    password     varchar(255),
    student_id   varchar(255),
    role_id      bigint,
    CONSTRAINT fk_roles FOREIGN KEY (role_id) REFERENCES public.roles(id)
);


CREATE TABLE public.students_voice
(
    id           bigserial primary key,
    candidate_id bigint REFERENCES public.users,
    student_id   bigint REFERENCES public.users
);


