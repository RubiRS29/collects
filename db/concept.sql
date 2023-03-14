-- public.concept definition

-- Drop table

-- DROP TABLE public.concept;

CREATE TABLE public.concept (
                                concept_id bigserial NOT NULL,
                                app_id int8 NOT NULL,
                                description varchar(255) NULL,
                                operation int4 NULL,
                                CONSTRAINT concept_pkey PRIMARY KEY (concept_id)
);