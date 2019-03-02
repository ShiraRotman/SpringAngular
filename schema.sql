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

CREATE TABLE public.votes (
  vote_id BIGINT NOT NULL,
  campaign_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  voted_user_id INTEGER NOT NULL,
  PRIMARY KEY(vote_id)
)
WITH (oids = false);

ALTER TABLE public.votes ADD CONSTRAINT votes_fk FOREIGN KEY (campaign_id) REFERENCES public.campaigns(campaign_id)
    ON DELETE RESTRICT ON UPDATE RESTRICT NOT DEFERRABLE;
ALTER TABLE public.votes ADD CONSTRAINT votes_fk1 FOREIGN KEY (user_id) REFERENCES public.users(user_id)
    ON DELETE RESTRICT ON UPDATE RESTRICT NOT DEFERRABLE;
ALTER TABLE public.votes ADD CONSTRAINT votes_fk2 FOREIGN KEY (voted_user_id) REFERENCES public.users(user_id)
    ON DELETE RESTRICT ON UPDATE RESTRICT NOT DEFERRABLE;
ALTER TABLE public.votes ADD CONSTRAINT votes_ukey UNIQUE (campaign_id, user_id, voted_user_id) NOT DEFERRABLE;
ALTER TABLE public.votes ALTER COLUMN vote_id ADD GENERATED ALWAYS AS IDENTITY;