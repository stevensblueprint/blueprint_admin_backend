# Blueprint Admin Backend
Spring Java application that hosts the backend for our Blueprint Admin application. This project will help us manage Blueprint members.

## Running the service
Initialize docker container with postgres service. If you don't have docker installed you can install [here](https://docs.docker.com/engine/install/).
```
docker-compose up -d
```
Build the application
```
./gradlew build
```
Run the application. (You have to run the ```admin/BlueprintAdmin.java``` this is the entry point of the Spring application).

## How to connect to database in Docker Container?
Start the docker container
```
docker-compose up
```

Connect to psql shell
```
docker exec -it blueprint_admin_backend-postgres-1 psql -U blueprint_admin_backend
```
To see all the available tables run
```
\dt
```
To view data inside a Table
```
TABLE {table_name};
```
