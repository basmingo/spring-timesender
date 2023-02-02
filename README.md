# spring-timesender
<img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> <img src="https://upload.wikimedia.org/wikipedia/commons/6/68/Mariadb-seal-browntext.svg" alt="mariadb" width="160" height="40"/> <img src="https://upload.wikimedia.org/wikipedia/commons/0/05/Apache_kafka.svg" alt="apache kafka" width="40" height="40"/>
<img src="https://upload.wikimedia.org/wikipedia/commons/4/4e/Docker_%28container_engine%29_logo.svg" alt="docker" width="160" height="40"/>


The small testing task uses 3 microservices for messages sent between each other.

<b>Using stack: Spring Framework, Spring Data, MariaDB, Apache Kafka, Docker, JUnit. </b>

- m1 sends a message to m2 using HTTP request
- m2 sends a message to m3 using Kafka
- m3 sends a message back to the m1
- m1 storing the message in a MariaDB, logging amount of the messages generated during the period.
- repeat the process until it is explicitly stopped by a command.

<b>Deployment:</b> start docker-compose file in spring-timesender/docker

<b>Dependencies:</b>
- Kafka
- Zookeeper
- MariaDB
