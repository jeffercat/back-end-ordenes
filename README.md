# Título del Proyecto

_Proyecto Órdenes Entrevista Altiora Corp_

## Comenzando 🚀

_Estas instrucciones permitirán obtener una copia del proyecto en funcionamiento en su máquina local para propósitos de desarrollo y pruebas._
_1. Clonar el repositorio_
```
git clone https://jimendoza@bitbucket.org/jimendoza/back-end-ordenes.git
```
_2. Cambiarse a la rama master_
```
git checkout master
```
### Pre-requisitos 📋

_1. Crear una base datos MySql, con el nombre ordenes_db_

```
create database ordenes_db
```
_2. En el archivo applicaction.properties modificar las credendiales de la Base de Datos y tener disponible puerto 8080 que es donde correrá el back end_
```
spring.datasource.url=jdbc:mysql://localhost/ordenes_db?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=nombre_usuario
spring.datasource.password=constrasenia_usuario
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL57InnoDBDialect
spring.jpa.hibernate.ddl-auto=create
logging.level.org.hibernate.SQL=error
server.error.include-stacktrace=never
spring.jmx.enabled= false
spring.jackson.time-zone= America/Guayaquil
spring.jackson.locale= es_EC
```
_3. Tener instalado Spring Tool Suite 4 _
_4. Tener instalado java JDK 8_
_5. Decargar las dependencias que se encuentran en el pom.xml, esto se realiza haciendo update project_
_6. Ejecutar el proyecto
