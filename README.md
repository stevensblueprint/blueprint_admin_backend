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
