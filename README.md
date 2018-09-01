# spring-integration-mq

IBM MQ setup using docker.

```
docker volume create testqmgrdata
```

```
docker run --env LICENSE=accept --env MQ_QMGR_NAME=TESTQMGR --publish 1414:1414 --publish 9443:9443 --detach --volume testqmgrdata:/mnt/mqm --name testmq ibmcom/mq
```

Application receives a fixed format message and converts to another fixed format. 

http://fixedformat4j.ancientprogramming.com/ has been used for parsing and generating fixed format files. Swagger codegen has been  used for genarating the model classes with the fixedformat4j specific annotations. Mustache templates have been modified to achieve this.
