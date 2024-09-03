El proyecto fue realizado para Backend en SpringBoot, Postgre, y testeado en postman.

Los dos enpoint habilitados son:

Pokemon especifico y sus datos
http://localhost:8080/api/pokemon/{id}

Lista paginada de una generacion
http://localhost:8080/api/pokemon/generation/{id}?page=0&size=10 (http://localhost:8080/api/pokemon/generation/{id}?page={id}&size=10)

Para gestionar la autentificacion se creo un usuario de pruebas en Spring Security con las siguientes credenciales:

User: usuariodepruebas
Password: 1234

En Postgre o la base de datos que se utilice, (se puede cambiar su configuracion en application.properties, esta se dejo por defecto para ejecutar pruebas),
se creo una base de datos llamada pokeapi, donde se dejo configurada para que se crearan y borrar los datos cada vez que se inicie o detenga la aplicacion.

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.hibernate.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/pokeapi
spring.datasource.username= postgres
spring.datasource.password= root
spring.jpa.show-sql= true

![image](https://github.com/user-attachments/assets/379c7c2f-02d3-49a2-a98c-b03fc70e2702)


En PokeapiApplication podran encontrar la creacion del usuario, los roles y permisos (los cuales pide Spring Security).

Las entidates utilizadas se encuantran guardadas en 2 carpertas, dentro de la carpeta persitence, una es para la manejo de la informacion de los pokemons, y las solicutudes
requeridas, y otra para el manejo del usuario, roles y permisos.

![image](https://github.com/user-attachments/assets/0b9ff44d-6fbe-4cc1-885b-3143ae88af5d)

Se creo un controller solo para las consultas de los pokemons

y el la carpeta Config, se encuantra el archivo Security config, con el cual se configuro la denegacion a usuario no registrados en la aplicacion(no se encripto la contraseña para fines de pruebas).
