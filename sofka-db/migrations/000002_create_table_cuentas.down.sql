ALTER TABLE cuenta.cuentas DROP CONSTRAINT uk_numero_cuenta;
DROP TABLE IF EXISTS cuenta.cuentas;
DROP TRIGGER IF EXISTS created_at_cuentas_trgr ON cuenta.cuentas;
DROP TRIGGER IF EXISTS updated_at_cuentas_trgr ON cuenta.cuentas;