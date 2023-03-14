-- public.balance definition

-- Drop table

-- DROP TABLE public.balance;

CREATE TABLE public.balance (
                                balance_id bigserial NOT NULL,
                                app_id int8 NULL,
                                balance float8 NULL,
                                created_on timestamp NULL,
                                "date" timestamp NULL,
                                unit_id int8 NULL,
                                updated_on timestamp NULL,
                                CONSTRAINT balance_pkey PRIMARY KEY (balance_id)
);