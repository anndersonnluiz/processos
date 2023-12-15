CREATE DATABASE processos
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE IF NOT EXISTS public.processos
(
    id_processos integer NOT NULL,
    num_processos character varying(255) COLLATE pg_catalog."default",
    reu character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT processos_pkey PRIMARY KEY (id_processos)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.processos
    OWNER to postgres;