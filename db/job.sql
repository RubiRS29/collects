-- public.job definition

-- Drop table

-- DROP TABLE public.job;

CREATE TABLE public.job (
                            balance_id bigserial NOT NULL,
                            app_id int8 NULL,
                            created_on timestamp NULL,
                            cron int8 NULL,
                            "date" timestamp NULL,
                            quantity float8 NULL,
                            status varchar(255) NULL,
                            updated_on timestamp NULL,
                            concept_concept_id int8 NULL,
                            CONSTRAINT job_pkey PRIMARY KEY (balance_id)
);


-- public.job foreign keys

ALTER TABLE public.job ADD CONSTRAINT fk53ojaoyj5q0mwqqqji5c4xfq7 FOREIGN KEY (concept_concept_id) REFERENCES public.concept(concept_id);