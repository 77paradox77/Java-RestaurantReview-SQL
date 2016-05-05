--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cuisine; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE cuisine (
    id integer NOT NULL,
    cuisine_type character varying
);


ALTER TABLE cuisine OWNER TO "Guest";

--
-- Name: cuisine_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE cuisine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cuisine_id_seq OWNER TO "Guest";

--
-- Name: cuisine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE cuisine_id_seq OWNED BY cuisine.id;


--
-- Name: restaurant; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE restaurant (
    id integer NOT NULL,
    name character varying,
    description character varying,
    cuisine_id integer
);


ALTER TABLE restaurant OWNER TO "Guest";

--
-- Name: restaurant_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE restaurant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE restaurant_id_seq OWNER TO "Guest";

--
-- Name: restaurant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE restaurant_id_seq OWNED BY restaurant.id;


--
-- Name: review; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE review (
    id integer NOT NULL,
    info character varying,
    restaurant_id integer
);


ALTER TABLE review OWNER TO "Guest";

--
-- Name: review_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE review_id_seq OWNER TO "Guest";

--
-- Name: review_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE review_id_seq OWNED BY review.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY cuisine ALTER COLUMN id SET DEFAULT nextval('cuisine_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY restaurant ALTER COLUMN id SET DEFAULT nextval('restaurant_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY review ALTER COLUMN id SET DEFAULT nextval('review_id_seq'::regclass);


--
-- Data for Name: cuisine; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY cuisine (id, cuisine_type) FROM stdin;
\.


--
-- Name: cuisine_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cuisine_id_seq', 352, true);


--
-- Data for Name: restaurant; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY restaurant (id, name, description, cuisine_id) FROM stdin;
\.


--
-- Name: restaurant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('restaurant_id_seq', 299, true);


--
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY review (id, info, restaurant_id) FROM stdin;
\.


--
-- Name: review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('review_id_seq', 203, true);


--
-- Name: cuisine_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY cuisine
    ADD CONSTRAINT cuisine_pkey PRIMARY KEY (id);


--
-- Name: restaurant_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY restaurant
    ADD CONSTRAINT restaurant_pkey PRIMARY KEY (id);


--
-- Name: review_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

