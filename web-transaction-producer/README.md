## Producer of web transaction data

This application produces test data to `web-transaction-v1` Kafka topic.

It produces once per second automatically and also based on request to `http://127.0.0.1:8080/buy?userId=123`.


## Running producer

It can be run with Gradle locally:
```
> ./gradlew bookRun
```

Or in Docker:

```
> docker build . -t skkap/web-transaction-producer
> docker run -p 8080:8080 skkap/web-transaction-producer
```
* networking should be set up correctly for this process to be able to access Kafka cluster.

## Testing producer

To check if we actually producing something we can use CLI tool:
```
> ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic web-transaction-v1 --from-beginning
buyuser4

���Ӳ.webtr-producer-app
                       hankyu.local

buyuser2

���Ӳ.webtr-producer-app
                       hankyu.local

buyuser4

���Ӳ.webtr-producer-app
                       hankyu.local
```

## Properties

In real producer it is essential to set properties like `linger_ms` and `batch_size` correctly in order to 
balance load on the brokers and get optimal throughput.
