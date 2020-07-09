## Running Kafka locally with Docker Compose

1. Install [Docker Compose](https://docs.docker.com/compose/install/).

2. Run docker compose from the current directory.
```
> docker-compose up
```

## Check cluster

First, let's check if Kafka cluster is running properly.

We will use Kafka cli tools for that. 
Download [here](https://www.apache.org/dyn/closer.cgi?path=/kafka/2.5.0/kafka_2.12-2.5.0.tgz).

Then decompress files
```
> tar -xzf kafka_2.12-2.5.0.tgz
> cd kafka_2.12-2.5.0
```

Go to the `/kafka/kafka_2.12-2.5.0/bin`.

Run command to get list of topics.
```
> ./kafka-topics.sh --list --bootstrap-server localhost:9092
```

This should output list of system confluent topics in case of success, or connection error in case of failure.

## Creating topic

Run create topic command.

```
> ./kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3 --topic web-transaction-v1
```

We called topic `web-transaction-v1` as it will contain user transactions on web sites. `v1` is used for versioning in case of change of protocol.

If we run create topic command again, we will see our new topic:

Parameter `--replication-factor` specifies number of replicas of this topics among brocker nodes.

Parameter `--partitions` specifies number of partitions of the topic.

```
> ./kafka-topics.sh --describe --bootstrap-server localhost:9092 --topic web-transaction-v1
Topic: web-transaction-v1	PartitionCount: 3	ReplicationFactor: 1	Configs:
	Topic: web-transaction-v1	Partition: 0	Leader: 1	Replicas: 1	Isr: 1
	Topic: web-transaction-v1	Partition: 1	Leader: 1	Replicas: 1	Isr: 1
	Topic: web-transaction-v1	Partition: 2	Leader: 1	Replicas: 1	Isr: 1
```
