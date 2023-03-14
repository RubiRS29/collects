-- public.cron definition

-- Drop table

-- DROP TABLE public.cron;

CREATE TABLE public.cron (
                             balance_id bigserial NOT NULL,
                             CONSTRAINT cron_pkey PRIMARY KEY (balance_id)
);