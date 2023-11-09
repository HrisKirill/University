### Spring Boot project with the console interface for university.

1. The database is deployed using a docker container:

````
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=your_passsword -e MYSQL_DATABASE=your_database mysql:latest
````

2. Lector and Department are entities with relationships many-to-many using composite key.
3. To interact with database use BaseJpaRepository from io.hypersistence.
4. ConsoleInterface allows user to interact with the program via the console.