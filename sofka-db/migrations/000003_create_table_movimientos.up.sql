CREATE TABLE cuenta.movimientos
(
    movimiento_id   uuid          NOT NULL,
    cuenta_id       uuid          NOT NULL,
    fecha           timestamptz   NOT NULL,
    saldo           decimal(9, 2) NOT NULL,
    tipo_movimiento text,
    valor           decimal(9, 2) NOT NULL,
    created_at      timestamptz   NOT NULL DEFAULT NOW(),
    updated_at      timestamptz   NOT NULL DEFAULT NOW(),
    PRIMARY KEY (movimiento_id),
    CONSTRAINT "fk_cuenta_movimiento" FOREIGN KEY (cuenta_id)
    REFERENCES cuenta.cuentas (cuenta_id)
);

CREATE TRIGGER created_at_movimientos_trgr
    BEFORE UPDATE
    ON cuenta.movimientos
    FOR EACH ROW
EXECUTE PROCEDURE created_at_trigger();
CREATE TRIGGER updated_at_movimientos_trgr
    BEFORE UPDATE
    ON cuenta.movimientos
    FOR EACH ROW
EXECUTE PROCEDURE updated_at_trigger();