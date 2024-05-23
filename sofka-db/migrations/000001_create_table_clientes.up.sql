CREATE TABLE cliente.clientes
(
    cliente_id     uuid        NOT NULL,
    identificacion text,
    nombre         text,
    direccion      text,
    edad           int,
    genero         text,
    telefono       text,
    contrasena     text,
    estado         boolean,
    created_at     timestamptz NOT NULL DEFAULT NOW(),
    updated_at     timestamptz NOT NULL DEFAULT NOW(),
    PRIMARY KEY (cliente_id)
);

CREATE TRIGGER created_at_clientes_trgr
    BEFORE UPDATE
    ON cliente.clientes
    FOR EACH ROW
EXECUTE PROCEDURE created_at_trigger();
CREATE TRIGGER updated_at_clientes_trgr
    BEFORE UPDATE
    ON cliente.clientes
    FOR EACH ROW
EXECUTE PROCEDURE updated_at_trigger();