# tims-url-shortener
Create and return a shorter URL, store and redirect to original

DockerHub address: https://hub.docker.com/repository/docker/oneilltim/tims-url-shortener
commits here result in a new docker build as link added in dockerhub

Application deployed in AWS though not the Redis container, you could add a ElastiCache for Redis instance in AWS and use that by updating the application.properties file with the endpoint from AWS

## Clone and run the URL Shortener application:
```
$ git clone https://github.com/oneilltim/tims-url-shortener.git
$ cd tims-url-shortener
$ docker-compose up
```
## Access the Service:
### Create a Short URL:
  You can use a REST API Client like ARC for Chrome SEND a POST request to the frollowing URL: http://localhost:80/rest/url
  The body of the request should contain the URL that you want a Shorter Version of.
### Get the original URL given the Short verison
  http://localhost:80/rest/url/<shortURL>
  e.g. http://localhost:80/rest/url/aec3dbe9

## Application in AWS:
  To deploy to AWS I set my docker context to type:ecs and used docker compose up deploy to AWS:
```
>docker context create ecs myecscontext
...
Successfully created ecs context "myecscontext"
>docker context ls
NAME                TYPE                DESCRIPTION                               DOCKER ENDPOINT                  KUBERNETES ENDPOINT   ORCHESTRATOR
default *           moby                Current DOCKER_HOST based configuration   npipe:////./pipe/docker_engine                         swarm
myecscontext        ecs                 (eu-north-1)
>docker context use myecscontext
myecscontext
>docker context ls
NAME                TYPE                DESCRIPTION                               DOCKER ENDPOINT                  KUBERNETES ENDPOINT   ORCHESTRATOR
default             moby                Current DOCKER_HOST based configuration   npipe:////./pipe/docker_engine                         swarm
myecscontext *      ecs                 (eu-north-1)
>docker compose up
level=warning msg="services.hostname: unsupported attribute"
level=warning msg="services.build: unsupported attribute"
[+] Running 21/21="services.links: unsupported attribute"
 - tims-url-shortener              CreateComplete                                                                358.4s
 - Cluster                         CreateComplete                                                                  4.3s
 - DefaultNetwork                  CreateComplete                                                                  3.3s
 - MyappmainTCP8090TargetGroup     CreateComplete                                                                  0.0s
 - RedisTCP6379TargetGroup         CreateComplete                                                                  0.0s
 - LoadBalancer                    CreateComplete                                                                150.3s
 - MyappmainTaskExecutionRole      CreateComplete                                                                 12.3s
 - LogGroup                        CreateComplete                                                                  0.3s
 - RedisTaskExecutionRole          CreateComplete                                                                 12.3s
 - CloudMap                        CreateComplete                                                                 45.3s
 - DefaultNetworkIngress           CreateComplete                                                                  1.0s
 - Default8090Ingress              CreateComplete                                                                  1.0s
 - Default6379Ingress              CreateComplete                                                                  1.0s
 - MyappmainTaskDefinition         CreateComplete                                                                  2.0s
 - RedisTaskDefinition             CreateComplete                                                                  3.0s
 - RedisServiceDiscoveryEntry      CreateComplete                                                                  2.0s
 - MyappmainServiceDiscoveryEntry  CreateComplete                                                                  3.0s
 - RedisTCP6379Listener            CreateComplete                                                                  3.0s
 - MyappmainTCP8090Listener        CreateComplete                                                                  4.0s
 - RedisService                    CreateComplete                                                                 99.0s
 - MyappmainService                CreateComplete                                                                 89.7s

>docker compose ps
NAME                                                       SERVICE             STATUS              PORTS
task/tims-url-shortener/6aa149268de74d04a0b27c3ce99da5e5   myapp-main          Running             tims-LoadB-1DFAECFTCWS7C-21ccb31b0f9f4c8f.elb.us-east-1.amazonaws.com:8090->8090/tcp
task/tims-url-shortener/d5e66234348a4db98bfa845821d4b032   redis               Running             tims-LoadB-1DFAECFTCWS7C-21ccb31b0f9f4c8f.elb.us-east-1.amazonaws.com:6379->6379/tcp

```
  AWS App URL: https://tims-LoadB-1DFAECFTCWS7C-21ccb31b0f9f4c8f.elb.us-east-1.amazonaws.com/rest/url

