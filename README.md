El proyecto fue realizado para Backend en SpringBoot, Postgre, y testeado en postman.

Los dos enpoint habilitados son:
Pokémon especifico y sus datos http://localhost:8080/api/pokemon/{id}
Lista paginada de una generacion http://localhost:8080/api/pokemon/generation/{id}?page=0&size=10 (http://localhost:8080/api/pokemon/generation/{id}?page={id}&size=10)

Para gestionar la autentificación se creo un usuario de pruebas en Spring Security con las siguientes credenciales:
User: usuariodepruebas 
Password: 1234

En Postgre o la base de datos que se utilice, (se puede cambiar su configuración en application.properties, esta se dejó por defecto para ejecutar pruebas), 
se creó una base de datos llamada pokeapi, donde se dejó configurada para que se crearan y borrar los datos cada vez que se inicie o detenga la aplicación.

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.hibernate.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/pokeapi
spring.datasource.username= postgres
spring.datasource.password= root
spring.jpa.show-sql= true


![image](https://github.com/user-attachments/assets/379c7c2f-02d3-49a2-a98c-b03fc70e2702)


En PokeapiApplication podrán encontrar la creación del usuario, los roles y permisos (los cuales pide Spring Security).

Las entidades utilizadas se encuentran guardadas en 2 carpetas, dentro de la carpeta persitence, una es para el manejo 
de la información de los pokemons, y las solicitudes requeridas, y otra para el manejo del usuario, roles y permisos.

![image](https://github.com/user-attachments/assets/0b9ff44d-6fbe-4cc1-885b-3143ae88af5d)

Se creo un controller solo para las consultas de los pokemons

![image](https://github.com/user-attachments/assets/6a030774-4157-41e2-a9ae-86bd920a6a64)

y en la carpeta Config, se encuentra el archivo Security config, con el cual se configuro 
la denegación a usuario no registrados en la aplicación (no se encripto la contraseña para fines de pruebas).
