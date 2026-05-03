# SeguriTechGA8 - Integración de módulos

Proyecto académico para la evidencia GA8-220501096-AA1-EV01 del programa Análisis y Desarrollo de Software.

## Objetivo

Integrar módulos básicos de la aplicación web SeguriTech S.A. mediante Java, JSP, Servlets, JDBC y MySQL.

## Módulos incluidos

* Inicio de sesión
* Panel principal
* Usuarios
* Clientes
* Visitas técnicas / historial

## Requisitos

* JDK 11 o superior
* NetBeans IDE
* Apache Tomcat 9
* MySQL 8 o XAMPP con MySQL/MariaDB
* MySQL Connector/J

## Configuración de base de datos

1. Abrir MySQL Workbench o phpMyAdmin.
2. Ejecutar el script `database/seguritech\_ga8.sql`.
3. Validar que se cree la base de datos `seguritech\_ga8`.
4. Revisar la clase `Conexion.java`.
5. Si MySQL usa puerto 3307, cambiar `3306` por `3307`.

## Acceso de prueba

* Usuario: `admin`
* Contraseña: `1234`

## Evidencias sugeridas

* Captura de estructura del proyecto en NetBeans.
* Captura de la base de datos en MySQL.
* Captura de inicio de sesión.
* Captura del panel principal.
* Captura de usuarios listados desde MySQL.
* Captura de clientes registrados.
* Captura de visitas técnicas registradas.
* Captura de Git/GitHub con commits.

