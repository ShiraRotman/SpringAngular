CREATE TABLE public.campaigns (
  campaign_id BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
  campaign_name VARCHAR(50) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  CONSTRAINT campaigns_pkey PRIMARY KEY(campaign_id)
)
WITH (oids = false);

CREATE TABLE public.users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled BOOLEAN NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY(username)
)
WITH (oids = false);

CREATE TABLE public.authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  CONSTRAINT authorities_pkey PRIMARY KEY(username, authority),
  CONSTRAINT authorities_username_fkey FOREIGN KEY (username)
    REFERENCES public.users(username)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    NOT DEFERRABLE
)
WITH (oids = false);