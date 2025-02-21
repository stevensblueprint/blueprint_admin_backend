# Blueprint Admin Backend
Spring Java application that hosts the backend for our Blueprint Admin application. This project will help us manage Blueprint members.

## Running the service
Copy the environment variables from .env.example
```
cp .env.example .env
```
Initialize docker container with postgres service. If you don't have docker installed you can install [here](https://docs.docker.com/engine/install/).
```
docker-compose up -d
```
Run the application. (You have to run the ```admin/BlueprintAdmin.java``` this is the entry point of the Spring application).
Else, you can use the following command to run the Spring application:
```
./gradlew bootRun
```
You should see the following output in your terminal
```
o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
com.sitblueprint.admin.BlueprintAdmin    : Started BlueprintAdmin in 2.255 seconds (process running for 2.392)
```

You can also run the server directly from the terminal

## Troubleshooting
If you are having issues running the postgres db with Docker try the following

### For Windows
```powershell
docker-compose down -v
Remove-Item postgres-data
docker-compose up --build
```

### For Mac/Linux
```bash
docker-compose down -v
rm -rf postgres-data
docker-compose up --build
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
