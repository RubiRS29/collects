-- public."transaction" definition

-- Drop table

-- DROP TABLE public."transaction";

CREATE TABLE public."transaction" (
                                      transaction_id bigserial NOT NULL,
                                      app_id int8 NOT NULL,
                                      created_on timestamp NULL,
                                      "date" timestamp NULL,
                                      description varchar(255) NULL,
                                      quantity float8 NULL,
                                      reference varchar(255) NULL,
                                      reference_id varchar(20) NULL,
                                      unit_id int8 NOT NULL,
                                      unit_name varchar(255) NULL,
                                      updated_on timestamp NULL,
                                      concept_concept_id int8 NULL,
                                      CONSTRAINT transaction_pkey PRIMARY KEY (transaction_id)
);


-- public."transaction" foreign keys

ALTER TABLE public."transaction" ADD CONSTRAINT fk3tiys80gdxii6sxf32mtfgwdj FOREIGN KEY (concept_concept_id) REFERENCES public.concept(concept_id);