# JDC
JAVA DEVELOPMENT CHALLENGE



### Setup Environment

1. Install Docker
- reference guide: https://docs.docker.com/engine/install/ubuntu/

2. Install and run Mysql
    - First step. Checkout and run mysql
   ```
   docker run --name mysql_jdc -e 'MYSQL_ROOT_PASSWORD=root' -p '3309:3306' -v ~/MountVolumes/mysql_jdc mysql
   ```
   - Second step. Exec mysql docker
   ```
   docker exec -it mysql sh 
   ```
   - Third step. Connect mysql
   ```
   mysql -uroot -proot
   ```
    - 4th step. Create JDC database
   ```
   CREATE DATABASE jdc_shopping_online;
   ```

3. Install and run Kafka
    - First step. We need to install zookeeper

   ```
   docker run --name zookeeper -p '0.0.0.0:2181:2181' -e'ZOOKEEPER_CLIENT_PORT=2181' confluentinc/cp-zookeeper:5.5.0
   ```
   
    - Second step. We can install kafka

   ```
   docker run --name kafka -p '9092:9092' -e 'KAFKA_ZOOKEEPER_CONNECT=2181' -e 'KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:29092' -v ~/MountVolumes/kafka confluentinc/cp-kafka:5.5.0
   ```

4. Install and run Redis
   ```
    docker run --name redis -p '6379:6379' -v ~/MountVolumes/redis redis
   ```
   
5. Install and run Mongo
   ```
   docker run --name mongo_jdc -e 'ME_CONFIG_MONGODB_ADMINUSERNAME=root' -e 'ME_CONFIG_MONGODB_ADMINUSERNAME=root' -p '27018:27017' -v ~/MountVolumes/mongo_jdc mongo

   ```
   
6. Setup and install Maven
   - If we are not yet install Maven on local. we can refer this guide as below:
     https://maven.apache.org/install.html

7. Checkout and run source code from git
   ```
   https://github.com/tiendao21417/jdc/tree/dev

   ```

### Directory layout 
Now our project have structure as below.

```

├── src                                         # Source files
  ├── main
    ├── java.jdc.onlineshopping                 # Root package
      ├── aop                                   # AOP config/handler
      ├── app                                   # Configuration folder
        ├── api                                 # JDC Public module
          ├── mapper                            # API mapper folder
          ├── service                           # API service folder
          ├── web.rest                          # API Controler Folder
            ├── dto                             # API Data Transfer Object Folder
        ├── ops                                 # JDC Ops module
          ├── mapper                            # OPS mapper folder
          ├── service                           # OPS service folder
          ├── web.rest                          # OPS Controler Folder
            ├── dto                             # OPS Data Transfer Object Folder          
        ├── worker                              # JDC Worker module
          ├── service                           # Worker service folder
      ├── config                                # Configuration folder
      ├── domain                                # Entities folder
      ├── repository                            # Repository folder
      ├── service                               # Common service folder
      ├── mapper                                # Common mapper folder
      ├── constant                              # Common constaint folder
      ├── utils    
      ├── web.rest.dto                          # Common Data Transfer Object Folder                            
    └── OnlineShoppingApplication.java          # Application Main Class
    ├── resources                               # Resource folder
  └──  test                       # Test Folder
├── target                        # Build folder
├── pom.xml                       # Maven config file
└── README.md                     # Readme file

```

### RUN SERVICES

1. To run JDC project:
- Install Maven Dependency
```
mvn dependency:resolve
```
- Run All test
```
mvn test
```

- Run Project
```
mvn spring-boot:run -Dspring.profiles.active=be
```