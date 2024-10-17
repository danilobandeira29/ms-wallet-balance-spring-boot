# Wallet Balance

## Description
`wallet-balance` spring boot application responsible for get accounts balance and consumer kafka's topic "balances".

## Requirements

You need to install:

- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [Kafka](https://kafka.apache.org/quickstart) running locally
- SQLite (or another database, depends on `application.properties` file)

## Config

`application.properties` should be in `src/main/resources/` directory within:

```properties
spring.application.name=wallet-balance
spring.devtools.livereload.enabled=true

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=wallet
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer

server.port=8090

spring.datasource.url=jdbc:sqlite::memory:
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

### Environment Variables(Optional)

If necessary, you can configure this envs:

- `SPRING_KAFKA_BOOTSTRAP_SERVERS`: kafka's server
- `SPRING_DATASOURCE_URL`: database url

## Running the project

### Step 1

```bash
git clone https://github.com/wallet-balance/wallet-balance.git
cd wallet-balance
```

### Step 2

1. **Compile**

   ```bash
   mvn clean install
   ```

2. **Running**(at port 8090, depends on `application.properties` file)

   ```bash
   mvn spring-boot:run
   ```

   or:

   ```bash
   java -jar target/wallet-balance-0.0.1-SNAPSHOT.jar
   ```

## Tests

```bash
mvn test
```

## Know Issues

- Kafka should be running before this project.
- Verify `application.properties` encoding to be utf-8.
