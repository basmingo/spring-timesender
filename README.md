# spring-timesender

small testing task, uses 3 microservices for a messages sending between each other.

Using stack: Spring Framework, Spring Data, MariaDB, Apache Kafka, Docker, JUnit.

- m1 sends a message to m2 using http request
- m2 sends a message to m3 using Kafka
- m3 sends a message back to the m1
- m1 storing the message in a MariaDB, log amount of the messages generated duting the period


Deployment: start docker-compose file in spring-timesender/docker

Dependencies:
- Kafka
- Zookeeper
- MariaDB
