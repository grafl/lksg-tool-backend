-- drop view lksg_tool.v_incidents;
-- drop view lksg_tool.v_companies_bpns_x;
-- drop view lksg_tool.v_companies_bpns;
-- drop table lksg_tool.ess_incident_responses;
-- drop table lksg_tool.ess_incident_requests;
-- drop table lksg_tool.bpn_bpns;
-- drop table lksg_tool.bpns;
-- drop table lksg_tool.companies;
-- drop table lksg_tool.flyway_schema_history;

-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE SCHEMA IF NOT EXISTS lksg_tool AUTHORIZATION lksg_tool;

CREATE TABLE lksg_tool.companies (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    street character varying(255),
    nr character varying(8),
    zip_code character varying(8),
    location character varying(255),
    country character varying(255),
    PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS lksg_tool.companies OWNER to lksg_tool;
INSERT INTO lksg_tool.companies (id, name, street, nr, zip_code, location, country) VALUES (uuid_generate_v4(), 'Lieferant AG', 'Waage', '11', '5214', 'Thönger', 'Germany');
INSERT INTO lksg_tool.companies (id, name, street, nr, zip_code, location, country) VALUES (uuid_generate_v4(), 'Mobil-Aktion AG', 'Alte Mühle Langenthal Mühleweg', '23', '4900', 'Langenthal', 'Swiss');
INSERT INTO lksg_tool.companies (id, name, street, nr, zip_code, location, country) VALUES (uuid_generate_v4(), 'Versuch GmbH', 'Rose', '2', '1242', 'Linden', 'Germany');
INSERT INTO lksg_tool.companies (id, name, street, nr, zip_code, location, country) VALUES (uuid_generate_v4(), 'NeueFirma KG', 'Winter', '36', '3360', 'Thöringen', 'Germany');
INSERT INTO lksg_tool.companies (id, name, street, nr, zip_code, location, country) VALUES (uuid_generate_v4(), 'PKW AG', 'Vikingen', '125', '6321', 'Wissenthor', 'Germany');
INSERT INTO lksg_tool.companies (id, name, street, nr, zip_code, location, country) VALUES (uuid_generate_v4(), 'Test OHG', 'Haupstrasse', '1', '2252', 'Dorfingen', 'Germany');
INSERT INTO lksg_tool.companies (id, name, street, nr, zip_code, location, country) VALUES (uuid_generate_v4(), 'Auto GmbH', 'Schischup', '99', '4900', 'Rosenheim', 'Germany');
-- -----------------------------------------------------------------------------
CREATE TABLE lksg_tool.bpns (
    bpn character varying(16) NOT NULL,
    PRIMARY KEY (bpn)
);
ALTER TABLE IF EXISTS lksg_tool.bpns OWNER to lksg_tool;
INSERT INTO lksg_tool.bpns (bpn) VALUES('BPNS00000003AXS3');
INSERT INTO lksg_tool.bpns (bpn) VALUES('BPNS00000003B2OM');
INSERT INTO lksg_tool.bpns (bpn) VALUES('BPNS00000003B3NX');
INSERT INTO lksg_tool.bpns (bpn) VALUES('BPNS00000003B5MJ');
INSERT INTO lksg_tool.bpns (bpn) VALUES('BPNS00000003B6LU');
INSERT INTO lksg_tool.bpns (bpn) VALUES('BPNS000000815DMY');
INSERT INTO lksg_tool.bpns (bpn) VALUES('BPNS000004711DMY');
-- -----------------------------------------------------------------------------
CREATE TABLE lksg_tool.company_bpns (
     company character varying(36) NOT NULL,
     bpn character varying(36) NOT NULL,
     PRIMARY KEY (company, bpn)
);
ALTER TABLE IF EXISTS lksg_tool.company_bpns OWNER to lksg_tool;
ALTER TABLE IF EXISTS lksg_tool.company_bpns
    ADD CONSTRAINT fk_cbs_company FOREIGN KEY (company)
        REFERENCES lksg_tool.companies (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;
ALTER TABLE IF EXISTS lksg_tool.company_bpns
    ADD CONSTRAINT fk_cbs_bpn FOREIGN KEY (bpn)
        REFERENCES lksg_tool.bpns (bpn) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;
INSERT INTO lksg_tool.company_bpns (company, bpn) VALUES ((SELECT id FROM lksg_tool.companies WHERE name = 'Auto GmbH'), 'BPNS000004711DMY');
INSERT INTO lksg_tool.company_bpns (company, bpn) VALUES ((SELECT id FROM lksg_tool.companies WHERE name = 'Lieferant AG'), 'BPNS00000003AXS3');
INSERT INTO lksg_tool.company_bpns (company, bpn) VALUES ((SELECT id FROM lksg_tool.companies WHERE name = 'Mobil-Aktion AG'), 'BPNS00000003B2OM');
INSERT INTO lksg_tool.company_bpns (company, bpn) VALUES ((SELECT id FROM lksg_tool.companies WHERE name = 'NeueFirma KG'), 'BPNS00000003B5MJ');
INSERT INTO lksg_tool.company_bpns (company, bpn) VALUES ((SELECT id FROM lksg_tool.companies WHERE name = 'PKW AG'), 'BPNS00000003B6LU');
INSERT INTO lksg_tool.company_bpns (company, bpn) VALUES ((SELECT id FROM lksg_tool.companies WHERE name = 'Test OHG'), 'BPNS000000815DMY');
INSERT INTO lksg_tool.company_bpns (company, bpn) VALUES ((SELECT id FROM lksg_tool.companies WHERE name = 'Versuch GmbH'), 'BPNS00000003B3NX');
-- -----------------------------------------------------------------------------
CREATE TABLE lksg_tool.ess_incident_requests (
    id character varying(36) NOT NULL,
    company character varying(36) NOT NULL,
    bpn character varying(36) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uc_ess_incident_requests UNIQUE (company, bpn)
);
ALTER TABLE IF EXISTS lksg_tool.ess_incident_requests OWNER to lksg_tool;
ALTER TABLE IF EXISTS lksg_tool.ess_incident_requests
    ADD CONSTRAINT fk_eireqs_company FOREIGN KEY (company)
        REFERENCES lksg_tool.companies (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;
ALTER TABLE IF EXISTS lksg_tool.ess_incident_requests
    ADD CONSTRAINT fk_eireqs_bpn FOREIGN KEY (bpn)
        REFERENCES lksg_tool.bpns (bpn) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;
-- -----------------------------------------------------------------------------
CREATE TABLE lksg_tool.ess_incident_responses (
    request character varying(36) NOT NULL,
    job character varying(36) NOT NULL,
    status character varying(16) NOT NULL,
    impacted character varying(16),
    response character varying(255) NOT NULL,
    PRIMARY KEY (request, job)
);
ALTER TABLE IF EXISTS lksg_tool.ess_incident_responses OWNER to lksg_tool;
ALTER TABLE IF EXISTS lksg_tool.ess_incident_responses
    ADD CONSTRAINT fk_eir_request FOREIGN KEY (request)
        REFERENCES lksg_tool.ess_incident_requests (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;
