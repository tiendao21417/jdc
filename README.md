# JDC-JAVA DEVELOPMENT CHALLENGE

### INTRODUCTION
Problem Statement
A small start-up named "iCommerce" wants to build a very simple online shopping application to sell
their products. In order to get to the market quickly, they just want to build an MVP version with a very
limited set of functionalities:
1. The application is simply a simple web page that shows all products on which customers can
   filter and search for products based on different criteria such as product category, name, price,
   brand, colour.
2. If the customer finds a product that they like, they can view its details and add it to their
   shopping cart and proceed to place an order.
3. No online payment is supported yet. The customer is required to pay by cash when the product
   got delivered.

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

### APIS
- We can get full api curl from:
  https://github.com/tiendao21417/jdc/blob/main/documentation/JDC.postman_collection.json
  
   Get it and import this one to postman.
  

- Base endpoint:
   ```http://localhost:8080```
  
This is detail on below:
#### MANAGER
   The manager has a role in product management. To use OPS APIs we need to OPS account for accept role.

   Register new Manage Account

   - POST `{{base_endpoint}}/ops/v1/register`Create Ops account

   - POST `{{base_endpoint}}/ops/v1/login`Login to OPS

   
1. Brand management APIs.
   - POST `{{base_endpoint}}/ops/v1/brands`Create new Brand
   - GET `{{base_endpoint}}/ops/v1/brands` Get brand list
   - POST `{{base_endpoint}}/ops/v1/brands/{id}`Update exist Brand
   - GET `{{base_endpoint}}/ops/v1/brands/{id}` Get brand detail
   
2. Category management APIs.
   - POST `{{base_endpoint}}/ops/v1/category`Create new Category
   - GET `{{base_endpoint}}/ops/v1/category` Get Category list
   - POST `{{base_endpoint}}/ops/v1/category/{id}`Update exist Category
   - GET `{{base_endpoint}}/ops/v1/category/{id}` Get Category detail
   
3. Product management APIs.
   - POST `{{base_endpoint}}/ops/v1/product`Create new Product
   - GET `{{base_endpoint}}/ops/v1/product` Get Product list
   - POST `{{base_endpoint}}/ops/v1/product/{id}`Update exist Product
   - GET `{{base_endpoint}}/ops/v1/product/{id}` Get Product detail


### CUSTOMER
   
   The customer can search, view product detail, add to cart and create order.
   - GET `{{base_endpoint}}/api/v1/products` Get Product list by filter.
   - GET `{{base_endpoint}}/api/v1/products/{id}` Get Product detail.
   - POST `{{base_endpoint}}/api/v1/cart` Add/remove product to cart.
   - GET `{{base_endpoint}}/api/v1/cart` view cart detail.
   - POST `{{base_endpoint}}/api/v1/orders` Create new order.
   - GET `{{base_endpoint}}/api/v1/orders/{id}` view order detail.


