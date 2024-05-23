CREATE TABLE cuenta.cuentas
(
    cuenta_id     uuid          NOT NULL,
    cliente_id    uuid,
    tipo_cuenta   text,
    numero_cuenta text,
    estado        boolean,
    saldo_inicial decimal(9, 2) NOT NULL,
    created_at    timestamptz   NOT NULL DEFAULT NOW(),
    updated_at    timestamptz   NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_numero_cuenta UNIQUE (numero_cuenta),
    PRIMARY KEY (cuenta_id)
);

CREATE TRIGGER created_at_cuentas_trgr
    BEFORE UPDATE
    ON cuenta.cuentas
    FOR EACH ROW
EXECUTE PROCEDURE created_at_trigger();
CREATE TRIGGER updated_at_cuentas_trgr
    BEFORE UPDATE
    ON cuenta.cuentas
    FOR EACH ROW
EXECUTE PROCEDURE updated_at_trigger();



