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


## Available Endpoints
For users:
```
GET /api/v1/user/all
```
```
GET /api/v1/user/{userId}
```
```
POST /api/v1/user/
```
```
PUT /api/v1/user/
```
```
DELETE /api/v1/user/
```
```
POST /api/v1/user/enable/{userId}
```
```
POST /api/v1/user/disable/{userId}
```
```
PUT /api/v1/user/reset_password
```
## How to connect to database in Docker Container?
Start the docker container
```
docker-compose up
```

Connect to psql shell
```
docker exec -it blueprint_admin_backend-postgres-1 psql -U blueprint_admin_backend -d postgres
```
To see all the available tables run
```
\dt
```
To view data inside a Table
```
TABLE {table_name};
```
