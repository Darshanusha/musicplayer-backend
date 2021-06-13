--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3 (Debian 13.3-1.pgdg100+1)
-- Dumped by pg_dump version 13.3 (Debian 13.3-1.pgdg100+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: trigger_insert_timestamp(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.trigger_insert_timestamp() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  NEW.creation_date = NOW();
  new.update_date = NOW();
  RETURN NEW;
END;
$$;


ALTER FUNCTION public.trigger_insert_timestamp() OWNER TO postgres;

--
-- Name: trigger_update_timestamp(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.trigger_update_timestamp() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  NEW.update_date = NOW();
  RETURN NEW;
END;
$$;


ALTER FUNCTION public.trigger_update_timestamp() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admin_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin_table (
    aname character varying(50) NOT NULL,
    pwd character varying(50) NOT NULL,
    aid bigint NOT NULL,
    creation_date timestamp(0) without time zone DEFAULT now(),
    update_date timestamp(0) without time zone
);


ALTER TABLE public.admin_table OWNER TO postgres;

--
-- Name: admin_table_aid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.admin_table ALTER COLUMN aid ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.admin_table_aid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: friends_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.friends_list (
    uid bigint NOT NULL,
    fid bigint
);


ALTER TABLE public.friends_list OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    role_id integer NOT NULL,
    role character varying(10) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: songs_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.songs_details (
    musicid bigint NOT NULL,
    music_name character varying(100) NOT NULL,
    artist character varying(50),
    movie character varying(50),
    m_name character varying(50),
    m_link character varying(300) NOT NULL,
    isenabled boolean DEFAULT true,
    count bigint DEFAULT 0,
    keywords character varying(100),
    stars integer DEFAULT 0,
    creation_date timestamp(0) without time zone DEFAULT now(),
    update_date timestamp(0) without time zone,
    release_date date
);


ALTER TABLE public.songs_details OWNER TO postgres;

--
-- Name: songs_details_musicid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.songs_details ALTER COLUMN musicid ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.songs_details_musicid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: user_priv; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_priv (
    uid bigint NOT NULL,
    musicid bigint,
    isstared boolean DEFAULT false,
    priv_id bigint NOT NULL
);


ALTER TABLE public.user_priv OWNER TO postgres;

--
-- Name: user_priv_priv_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.user_priv ALTER COLUMN priv_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_priv_priv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: user_pub; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_pub (
    uid bigint NOT NULL,
    musicid bigint,
    isstared boolean DEFAULT false,
    pub_id bigint NOT NULL
);


ALTER TABLE public.user_pub OWNER TO postgres;

--
-- Name: user_pub_pub_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.user_pub ALTER COLUMN pub_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_pub_pub_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: user_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_role (
    uid integer NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.user_role OWNER TO postgres;

--
-- Name: user_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_table (
    uid bigint NOT NULL,
    uname character varying(50) NOT NULL,
    u_auth character varying(50),
    pwd character varying(50) NOT NULL,
    isenabled boolean DEFAULT true NOT NULL,
    creation_date timestamp(0) without time zone DEFAULT now(),
    update_date timestamp(0) without time zone,
    dob date,
    phone_no character varying(15),
    email character varying(30),
    state character varying(10),
    country character varying(15)
);


ALTER TABLE public.user_table OWNER TO postgres;

--
-- Name: user_table_uid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.user_table ALTER COLUMN uid ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_table_uid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: admin_table admin_table_aname_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin_table
    ADD CONSTRAINT admin_table_aname_key UNIQUE (aname);


--
-- Name: admin_table admin_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin_table
    ADD CONSTRAINT admin_table_pkey PRIMARY KEY (aid);


--
-- Name: friends_list friends_list_uid_fid_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friends_list
    ADD CONSTRAINT friends_list_uid_fid_key UNIQUE (uid, fid);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- Name: role role_role_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_role_key UNIQUE (role);


--
-- Name: songs_details songs_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.songs_details
    ADD CONSTRAINT songs_details_pkey PRIMARY KEY (musicid);


--
-- Name: user_role uk_uid; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT uk_uid UNIQUE (uid);


--
-- Name: user_role unique_row; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT unique_row UNIQUE (uid, role_id);


--
-- Name: user_priv user_priv_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_priv
    ADD CONSTRAINT user_priv_pkey PRIMARY KEY (priv_id);


--
-- Name: user_priv user_priv_uid_musicid_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_priv
    ADD CONSTRAINT user_priv_uid_musicid_key UNIQUE (uid, musicid);


--
-- Name: user_pub user_pub_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_pub
    ADD CONSTRAINT user_pub_pkey PRIMARY KEY (pub_id);


--
-- Name: user_pub user_pub_uid_musicid_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_pub
    ADD CONSTRAINT user_pub_uid_musicid_key UNIQUE (uid, musicid);


--
-- Name: user_table user_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_pkey PRIMARY KEY (uid);


--
-- Name: user_table user_table_u_auth_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_u_auth_key UNIQUE (u_auth);


--
-- Name: user_table user_table_uname_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_uname_key UNIQUE (uname);


--
-- Name: songs_details_m_link_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX songs_details_m_link_idx ON public.songs_details USING btree (m_link);


--
-- Name: admin_table insert_timestamp; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER insert_timestamp BEFORE INSERT ON public.admin_table FOR EACH ROW EXECUTE FUNCTION public.trigger_insert_timestamp();


--
-- Name: songs_details insert_timestamp; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER insert_timestamp BEFORE INSERT ON public.songs_details FOR EACH ROW EXECUTE FUNCTION public.trigger_insert_timestamp();


--
-- Name: user_table insert_timestamp; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER insert_timestamp BEFORE INSERT ON public.user_table FOR EACH ROW EXECUTE FUNCTION public.trigger_insert_timestamp();


--
-- Name: admin_table update_timestamp; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER update_timestamp BEFORE UPDATE ON public.admin_table FOR EACH ROW EXECUTE FUNCTION public.trigger_update_timestamp();


--
-- Name: songs_details update_timestamp; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER update_timestamp BEFORE UPDATE ON public.songs_details FOR EACH ROW EXECUTE FUNCTION public.trigger_update_timestamp();


--
-- Name: user_table update_timestamp; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER update_timestamp BEFORE UPDATE ON public.user_table FOR EACH ROW EXECUTE FUNCTION public.trigger_update_timestamp();


--
-- Name: user_role fk5cwpllhe458f6j6fb7rmhfyt2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fk5cwpllhe458f6j6fb7rmhfyt2 FOREIGN KEY (uid) REFERENCES public.user_table(uid);


--
-- Name: user_role fk_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES public.role(role_id);


--
-- Name: user_role fk_user_table; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fk_user_table FOREIGN KEY (uid) REFERENCES public.user_table(uid);


--
-- Name: user_role fka68196081fvovjhkek5m97n3y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES public.role(role_id);


--
-- Name: user_role fkmmuf9x5p4eo2itv92p1txg3yh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fkmmuf9x5p4eo2itv92p1txg3yh FOREIGN KEY (uid) REFERENCES public.user_table(uid);


--
-- Name: friends_list friends_list_fid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friends_list
    ADD CONSTRAINT friends_list_fid_fkey FOREIGN KEY (fid) REFERENCES public.user_table(uid) ON DELETE CASCADE;


--
-- Name: friends_list friends_list_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friends_list
    ADD CONSTRAINT friends_list_uid_fkey FOREIGN KEY (uid) REFERENCES public.user_table(uid) ON DELETE CASCADE;


--
-- Name: user_priv user_priv_musicid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_priv
    ADD CONSTRAINT user_priv_musicid_fkey FOREIGN KEY (musicid) REFERENCES public.songs_details(musicid) ON DELETE CASCADE;


--
-- Name: user_priv user_priv_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_priv
    ADD CONSTRAINT user_priv_uid_fkey FOREIGN KEY (uid) REFERENCES public.user_table(uid) ON DELETE CASCADE;


--
-- Name: user_pub user_pub_musicid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_pub
    ADD CONSTRAINT user_pub_musicid_fkey FOREIGN KEY (musicid) REFERENCES public.songs_details(musicid) ON DELETE CASCADE;


--
-- Name: user_pub user_pub_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_pub
    ADD CONSTRAINT user_pub_uid_fkey FOREIGN KEY (uid) REFERENCES public.user_table(uid) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

