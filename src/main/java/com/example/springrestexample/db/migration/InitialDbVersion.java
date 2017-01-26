package com.example.springrestexample.db.migration;


import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.api.migration.MigrationInfoProvider;
import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

public class InitialDbVersion implements SpringJdbcMigration, MigrationInfoProvider {

    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        jdbcTemplate.execute("CREATE TABLE public.address\n" +
                "(\n" +
                "  id integer NOT NULL,\n" +
                "  city character varying(255),\n" +
                "  \"number\" character varying(255),\n" +
                "  street character varying(255),\n" +
                "  CONSTRAINT address_pkey PRIMARY KEY (id)\n" +
                ")\n" +
                "WITH (\n" +
                "OIDS=FALSE\n" +
                ");");

        jdbcTemplate.execute("ALTER TABLE public.address OWNER TO postgres;");

        jdbcTemplate.execute("CREATE TABLE public.employee\n" +
                "(\n" +
                "  id integer NOT NULL,\n" +
                "  firstname character varying(255),\n" +
                "  lastname character varying(255),\n" +
                "  address_id integer,\n" +
                "  CONSTRAINT employee_pkey PRIMARY KEY (id),\n" +
                "  CONSTRAINT fk759vmxo1jn0ql3orqinrieynp FOREIGN KEY (address_id)\n" +
                "  REFERENCES public.address (id) MATCH SIMPLE\n" +
                "  ON UPDATE NO ACTION ON DELETE NO ACTION\n" +
                ")\n" +
                "WITH (\n" +
                "OIDS=FALSE\n" +
                ");");

        jdbcTemplate.execute("ALTER TABLE public.employee\n" +
                "  OWNER TO postgres;");
    }

    @Override
    public MigrationVersion getVersion() {
        return MigrationVersion.fromVersion("1");
    }

    @Override
    public String getDescription() {
        return "Moves DB To State One (Initial)";
    }
}
