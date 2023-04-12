# IMDB ACADEMY PROJECT
## Installation
1. Clone the repository in your machine
```
https://github.com/gdeleiva/search-academy-project.git
```
2. Go to the project folder with a terminal or open a terminal in the project folder
```
cd search-academy-project
```
3. Build and deploy the application with docker using the following comand:
```
docker compose up --build -d
```
(make sure docker is running in your machine)

If you want to stop the application use the following command:
```
docker compose down
```
Sometimes elastic search wont stop and you will have to stop it from the docker app.

## Documentation
Once the system is deployed, you can find all the API documentation here, there is also the javadoc documentation:
```
http://localhost:8080/swagger-ui/index.html
```

## Asynchronous jobs
With the system deployed, you can check the status of asynchronous jobs, such as indexing, in the following link:
```
http://localhost:8000/dashboard/overview
```
Besides, you can check indexing progress connecting to localhost:9000 using cerebro:
```
http://localhost:9000
```
