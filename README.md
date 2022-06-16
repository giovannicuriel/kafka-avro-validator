# kafka-avro-validator
A simple application to send and receive messages using Avro and Kafka.

## How to use

1) Start Kafka and friends:

```
docker-compose up -d
```

2) Register the avro schema

```
./gradlew registerSchemasTask
```

3) Run the application

```
./gradlew bootRun
```

4) Send some events: for this, there is a few samples in `sample` folder. I used the [REST Client extension for VSCode](https://marketplace.visualstudio.com/items?itemName=humao.rest-client) (which is awsome).
