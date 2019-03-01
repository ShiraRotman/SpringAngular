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

ALTER TABLE public.campaigns ALTER COLUMN campaign_id TYPE INTEGER;
ALTER TABLE public.authorities DROP CONSTRAINT authorities_username_fkey RESTRICT;
ALTER TABLE public.authorities DROP CONSTRAINT authorities_pkey RESTRICT;
ALTER TABLE public.authorities ADD COLUMN authority_id BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY;
ALTER TABLE public.users DROP CONSTRAINT users_pkey RESTRICT;
ALTER TABLE public.users ADD COLUMN user_id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY;
ALTER TABLE public.users ADD UNIQUE (username);
ALTER TABLE public.authorities RENAME COLUMN username TO user_id;
ALTER TABLE public.authorities ALTER COLUMN user_id TYPE INTEGER USING 0;
ALTER TABLE public.authorities ADD CONSTRAINT authorities_fk FOREIGN KEY (user_id)
    REFERENCES public.users(user_id) ON DELETE CASCADE ON UPDATE RESTRICT NOT DEFERRABLE;