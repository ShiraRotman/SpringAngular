CREATE TABLE public.campaigns (
  campaign_id BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
  campaign_name VARCHAR(50) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  CONSTRAINT campaigns_pkey PRIMARY KEY(campaign_id)
)
WITH (oids = false);