# Coding Challenge Sofka

## Requisitos para la aplicación

- SO con Docker instalado. Esto se debe a que los tests de integración se construyeron utilizando [Testcontainers](https://testcontainers.com/)
- Java JDK 17
- Gradle 8
- Migrate CLI. La herramienta Migrate fue utilizada para crear los objetos en la base de datos. Para descargar el compilado haz click [aquí](https://github.com/golang-migrate/migrate/releases).
- PostgreSQL 15 o superior

## Creación de la base de datos y sus elementos

1. En el repositorio encontraras la carpeta sofka-db. Ingresa al directorio scripts. 
2. Carga el archivo `create_sofka_db.sh` a tu servidor de base de datos y ejecutalo. 
3. Una vez creados los objetos, ahora navega hacia el directorio migrations.
4. Ejecuta el siguiente comando para levantar las tablas y sus funciones (no olvides de reemplazar la variable DB_HOST por la dirección IP de tu servidor): 
```bash
   $ migrate -path=./migrations -database=postgres://sofka_test_user:018f9d54-2dff-72e4-9ba1-8497f2b9f72c@"${DB_HOST}"/sofka_test?sslmode=disable up
```

### Configuración de los proyectos

1. Dentro del repositorio encontrarás dos directorios: cliente y cuenta que contienen el código fuente para cada uno de los microservicios. 
2. Para cada uno de los directorios, edita los archivos `application.yml` y coloca la dirección IP de tu servidor PostgreSQL.
3. Ahora puedes ejecutar cada microservicio desde el IDE de tu preferencia.

### Construcción de los contenedores

1. Dentro del directorio cliente, ejecuta el siguiente comando:
```bash
   $ chmod +x gradlew
   $ ./gradlew bootJar
```
2. Copia tu archivo `application.yml`, al directorio ./config
3. Brinda permisos de ejecución al archivo ./config/entrypoint.sh:
```bash
   $ chmod +x ./config/entrypoint.sh
```
4. Brinda permisos de acceso al archivo ./build/libs/cliente-0.0.1-SNAPSHOT.jar:
```bash
   $ chmod +x ./build/libs/cliente-0.0.1-SNAPSHOT.jar
   $ chmod 777 ./build/libs/cliente-0.0.1-SNAPSHOT.jar
```
5. Ahora podremos construir el contenedor utilizando el siguiente comando:
```bash
   $ docker build --no-cache -t sofka/cliente:v1.0.0 .&> build.log
```
6. Para correr el contenedor:
```bash
   $ docker run -t --rm --name cliente --hostname cliente -d -p 8282:8080 sofka/cliente:v1.0.0
```
7. Para obtener un shell al contenedor:
```bash
   $ docker exec -it cliente bash
```
8. Para probar brevemente al contenedor:
```bash
   $ curl http://localhost:8282/clientes
```
9. Para el contenedor cuenta, puedes repetir los pasos 1-4, adaptando los nombres de los jars de salida.
10. Comandos para construir y correr el contenedor cuenta:
```bash
   $ docker build --no-cache -t sofka/cuenta:v1.0.0 .&> build.log
```

```bash
   $ docker run -t --rm --name cuenta --hostname cuenta -d -p 8080:8080 sofka/cuenta:v1.0.0
```


