Ejemplo Biblioteca Hibernate
============================

Descripción
-----------

Proyecto elaborado en NetBeans 8.0.x, que consiste en un ejemplo básico de aplicación web, el cual implementa la funcionalidad CRUD para una tabla (libros), a través de servlets y JSPs, utilizando el patrón MVC. Para el acceso a la base de datos se utiliza Hibernate 4.3. Para el diseño de la vista se incluye el framework Bootstrap 3.3.2.

Este ejemplo no incluye la validación de datos de formulario del lado del servidor, ni otras prácticas de seguridad que deben tenerse en consideración para un producto final.

Notas
-----

Para poder ejecutar el ejemplo, debe hacerse lo siguiente:
* Descargar el [conector JDBC para MySQL, versión 5.1.5][1]. Debe crearse la carpeta "libs" en el directorio del proyecto, y colocar allí el JAR del conector. Esta es la versión de conector a la que hace referencia el proyecto, pero podría sustituirse por otra, según la necesidad.
* Crear una base de datos llamada "biblioteca" en el servidor de MySQL, e importar el script "biblioteca.sql", incluido en la carpeta "sql". Este proyecto se ha probado con versiones 5.6.x de MySQL.
* Revisar y hacer las modificaciones necesarias en los parámetros de configuración de Hibernate (archivo src/java/hibernate.cfg.xml).

Es posible utilizar otros gestores de bases de datos, pero debe crearse la base de datos y tabla respectiva, incluir el conector JDBC apropiado como librería del proyecto, y hacer los ajustes respectivos en el archivo de configuración de Hibernate.

[1]: http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.5/mysql-connector-java-5.1.5.jar
