#!/bin/sh
set -e

psql -v ON_ERROR_STOP=1 --username postgres --dbname postgres <<-EOSQL
  CREATE DATABASE sofka_test;

  CREATE USER sofka_test_user WITH ENCRYPTED PASSWORD '018f9d54-2dff-72e4-9ba1-8497f2b9f72c';

  GRANT ALL PRIVILEGES ON DATABASE sofka_test TO sofka_test_user;

EOSQL

psql -v ON_ERROR_STOP=1 --username postgres --dbname "sofka_test" <<-EOSQL

  GRANT ALL ON SCHEMA public TO sofka_test_user;

  CREATE SCHEMA cliente;
  ALTER SCHEMA cliente OWNER TO sofka_test_user;
  GRANT ALL ON SCHEMA cliente TO sofka_test_user;
  GRANT ALL ON ALL TABLES IN SCHEMA cliente TO sofka_test_user;
  GRANT ALL ON ALL SEQUENCES IN SCHEMA cliente TO sofka_test_user;
  GRANT ALL ON ALL FUNCTIONS IN SCHEMA cliente TO sofka_test_user;

  CREATE SCHEMA cuenta;
  ALTER SCHEMA cuenta OWNER TO sofka_test_user;
  GRANT ALL ON SCHEMA cuenta TO sofka_test_user;
  GRANT ALL ON ALL TABLES IN SCHEMA cuenta TO sofka_test_user;
  GRANT ALL ON ALL SEQUENCES IN SCHEMA cuenta TO sofka_test_user;
  GRANT ALL ON ALL FUNCTIONS IN SCHEMA cuenta TO sofka_test_user;

  CREATE OR REPLACE FUNCTION created_at_trigger()
  RETURNS TRIGGER AS \$\$
  BEGIN
    NEW.created_at := OLD.created_at;
    RETURN NEW;
  END;
  \$\$ language 'plpgsql';

  CREATE OR REPLACE FUNCTION updated_at_trigger()
  RETURNS TRIGGER AS \$\$
  BEGIN
     IF row(NEW.*) IS DISTINCT FROM row(OLD.*) THEN
        NEW.updated_at = NOW();
        RETURN NEW;
     ELSE
        RETURN OLD;
     END IF;
  END;
  \$\$ language 'plpgsql';
EOSQL