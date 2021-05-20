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
  You can use a REST API Client like ARC for Chrome SEND a POST request to the frollowing URL: http://localhost:8090/rest/url
  The body of the request should contain the URL that you want a Shorter Version of.
### Get the original URL given the Short verison
  http://localhost:8090/rest/url/<shortURL>
  e.g. http://localhost:8090/rest/url/aec3dbe9

## Application in AWS:
  AWS App URL: http://3.138.137.28:8090/rest/url
  Copy of JSON for AWS Task definition:
  ```
  {
  "ipcMode": null,
  "executionRoleArn": "arn:aws:iam::608827584923:role/ecsTaskExecutionRole",
  "containerDefinitions": [
    {
      "dnsSearchDomains": null,
      "environmentFiles": null,
      "logConfiguration": {
        "logDriver": "awslogs",
        "secretOptions": null,
        "options": {
          "awslogs-group": "/ecs/first-run-task-definition",
          "awslogs-region": "us-east-2",
          "awslogs-stream-prefix": "ecs"
        }
      },
      "entryPoint": [],
      "portMappings": [
        {
          "hostPort": 8090,
          "protocol": "tcp",
          "containerPort": 8090
        },
        {
          "hostPort": 6379,
          "protocol": "tcp",
          "containerPort": 6379
        }
      ],
      "command": [],
      "linuxParameters": null,
      "cpu": 0,
      "environment": [],
      "resourceRequirements": null,
      "ulimits": null,
      "dnsServers": null,
      "mountPoints": [],
      "workingDirectory": null,
      "secrets": null,
      "dockerSecurityOptions": null,
      "memory": null,
      "memoryReservation": null,
      "volumesFrom": [],
      "stopTimeout": null,
      "image": "oneilltim/tims-url-shortener",
      "startTimeout": null,
      "firelensConfiguration": null,
      "dependsOn": null,
      "disableNetworking": null,
      "interactive": null,
      "healthCheck": null,
      "essential": true,
      "links": [],
      "hostname": null,
      "extraHosts": null,
      "pseudoTerminal": null,
      "user": null,
      "readonlyRootFilesystem": null,
      "dockerLabels": null,
      "systemControls": null,
      "privileged": null,
      "name": "tims-url-shortener-service"
    }
  ],
  "placementConstraints": [],
  "memory": "512",
  "taskRoleArn": null,
  "compatibilities": [
    "EC2",
    "FARGATE"
  ],
  "taskDefinitionArn": "arn:aws:ecs:us-east-2:608827584923:task-definition/first-run-task-definition:2",
  "family": "first-run-task-definition",
  "requiresAttributes": [
    {
      "targetId": null,
      "targetType": null,
      "value": null,
      "name": "com.amazonaws.ecs.capability.logging-driver.awslogs"
    },
    {
      "targetId": null,
      "targetType": null,
      "value": null,
      "name": "ecs.capability.execution-role-awslogs"
    },
    {
      "targetId": null,
      "targetType": null,
      "value": null,
      "name": "com.amazonaws.ecs.capability.docker-remote-api.1.19"
    },
    {
      "targetId": null,
      "targetType": null,
      "value": null,
      "name": "com.amazonaws.ecs.capability.docker-remote-api.1.18"
    },
    {
      "targetId": null,
      "targetType": null,
      "value": null,
      "name": "ecs.capability.task-eni"
    }
  ],
  "pidMode": null,
  "requiresCompatibilities": [
    "FARGATE"
  ],
  "networkMode": "awsvpc",
  "cpu": "256",
  "revision": 2,
  "status": "ACTIVE",
  "inferenceAccelerators": null,
  "proxyConfiguration": null,
  "volumes": []
}
  ```
