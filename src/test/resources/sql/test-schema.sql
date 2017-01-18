DROP TABLE employee;

DROP TABLE address;

CREATE TABLE public.address
(
  id integer NOT NULL,
  city character varying(255),
  "number" character varying(255),
  street character varying(255),
  CONSTRAINT address_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.address
  OWNER TO postgres;

CREATE TABLE public.employee
(
  id integer NOT NULL,
  firstname character varying(255),
  lastname character varying(255),
  address_id integer,
  CONSTRAINT employee_pkey PRIMARY KEY (id),
  CONSTRAINT fk759vmxo1jn0ql3orqinrieynp FOREIGN KEY (address_id)
  REFERENCES public.address (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.employee
  OWNER TO postgres;

