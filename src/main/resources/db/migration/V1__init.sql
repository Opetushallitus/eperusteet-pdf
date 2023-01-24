CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE dokumentti (
    id              bigint not null primary key,
    sisalto_id      bigint not null,
    tyyppi          character varying not null,
    tila            character varying not null,
    kieli           character varying not null,
    revision        int4 not null,
    aloitusaika     timestamp without time zone not null,
    valmistumisaika timestamp without time zone,
    dokumenttidata  oid
);
