## Consumer of web transaction data

This application consumes data from `web-transaction-v1` Kafka topic.

## Running consumer

It can be run with Gradle locally:
```
> ./gradlew bookRun
```

Or in Docker:

```
> docker build . -t skkap/web-transaction-consumer
> docker run skkap/web-transaction-consumer
```

* networking should be set up correctly for this process to be able to access Kafka cluster.

## Testing consumer

We can run consumer and producer simultaneously and observe output in the consumer terminal:

```
...
WebTransaction(type=buy, userId=user1)`.
WebTransaction(type=buy, userId=user4)`.
WebTransaction(type=buy, userId=user2)`.
WebTransaction(type=buy, userId=user0)`.
...
```
