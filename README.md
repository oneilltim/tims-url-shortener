# tims-url-shortener
Create and return a shorter URL, store and redirect to original

DockerHub address: https://hub.docker.com/repository/docker/oneilltim/tims-url-shortener
commits here result in a new docker build as link added in dockerhub

Application deployed in AWS though not the Redis container, you could add a ElastiCache for Redis instance in AWS and use that by updating the application.properties file with the endpoint from AWS

## To run the URL Shortener application:
```
$ git clone https://github.com/oneilltim/tims-url-shortener.git
$ cd tims-url-shortener
$ docker-compose up
```
