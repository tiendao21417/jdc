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
   - POST `{{base_endpoint}}/ops/v1/brands/multiple`Create new Brand list
   
2. Category management APIs.
   - POST `{{base_endpoint}}/ops/v1/category`Create new Category
   - GET `{{base_endpoint}}/ops/v1/category` Get Category list
   - POST `{{base_endpoint}}/ops/v1/category/{id}`Update exist Category
   - GET `{{base_endpoint}}/ops/v1/category/{id}` Get Category detail
   - POST `{{base_endpoint}}/ops/v1/category/multiple`Create new Category list
   
3. Product management APIs.
   - POST `{{base_endpoint}}/ops/v1/product`Create new Product
   - GET `{{base_endpoint}}/ops/v1/product` Get Product list
   - POST `{{base_endpoint}}/ops/v1/product/{id}`Update exist Product
   - GET `{{base_endpoint}}/ops/v1/product/{id}` Get Product detail
   - POST `{{base_endpoint}}/ops/v1/product/multiple`Create new Product list


#### CUSTOMER
   
   The customer can search, view product detail, add to cart and create order.
   - GET `{{base_endpoint}}/api/v1/products` Get Product list by filter.
   - GET `{{base_endpoint}}/api/v1/products/{id}` Get Product detail.
   - POST `{{base_endpoint}}/api/v1/cart` Add/remove product to cart.
   - GET `{{base_endpoint}}/api/v1/cart` view cart detail.
   - POST `{{base_endpoint}}/api/v1/orders` Create new order.
   - GET `{{base_endpoint}}/api/v1/orders/{id}` view order detail.

#### CREATE DATA AND TEST API STEP BY STEP

1. Create new manager account(OPS Account)
```
curl --location --request POST 'http://localhost:8080/ops/v1/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "tien.dao21417@gmail.com",
    "password": "abcd123456",
    "name": "Tien Dao",
    "full_address": "1D Nguyen Hue, District 1, HCM",
    "phone_number": "0907958077"
}'
```
2. Login to OPS
```
curl --location --request POST 'http://localhost:8080/ops/v1/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "tien.dao21417@gmail.com",
    "password": "abcd123456"
}'
```

In response, we have a collect token for authorization for all ops APIs.
this is example response on below:
```
{
    "meta": {
        "code": "200",
        "message": "Success"
    },
    "data": {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aWVuLmRhbzIxNDE3QGdtYWlsLmNvbSIsImlhdCI6MTYyNzIzMTMyNiwiZXhwIjoxNjI3MjQ5MzI2fQ.ZgSYNoD_OfZKNchPVWUL4z_pbJZUOH4DAoovP3OsKXFj7nnUtdhI1aTuGnzyDrLduLvDKxS2pduN5tHEHKO2bQ"
    }
}
```
3. To create Product we are need to create brand and categor first.
- Create Brand
```
curl --location --request POST 'http://localhost:8080/ops/v1/brands' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aWVuLmRhbzIxNDE3QGdtYWlsLmNvbSIsImlhdCI6MTYyNzIzMTMyNiwiZXhwIjoxNjI3MjQ5MzI2fQ.ZgSYNoD_OfZKNchPVWUL4z_pbJZUOH4DAoovP3OsKXFj7nnUtdhI1aTuGnzyDrLduLvDKxS2pduN5tHEHKO2bQ' \
--header 'Content-Type: application/json' \
--data-raw '{
    "code": "MK",
    "name": "MICHAEL KORS"
}'
```

Also we can create brand list by apis
```
curl --location --request POST 'http://localhost:8080/ops/v1/brands/multiple' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aWVuLmRhbzIxNDE3QGdtYWlsLmNvbSIsImlhdCI6MTYyNzIzMTMyNiwiZXhwIjoxNjI3MjQ5MzI2fQ.ZgSYNoD_OfZKNchPVWUL4z_pbJZUOH4DAoovP3OsKXFj7nnUtdhI1aTuGnzyDrLduLvDKxS2pduN5tHEHKO2bQ' \
--header 'Content-Type: application/json' \
--data-raw '{
    "items": [
        {
            "code": "RO",
            "name": "Rolex"
        },
        {
            "code": "APP",
            "name": "Patek Philippe"
        },
        {
            "code": "AP",
            "name": "Audemars Piguet"
        },{
            "code": "OM",
            "name": "Omega"
        },{
            "code": "WCS",
            "name": "WC Schaffhausen"
        },
        {
            "code": "NK",
            "name": "NIKE"
        },
        {
            "code": "MA",
            "name": "APPLE"
        },
        {
            "code": "HA",
            "name": "HANDMAKE"
        }
    ]
}'
```

- Create Category
```
curl --location --request POST 'http://localhost:8080/ops/v1/categories' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aWVuLmRhbzIxNDE3QGdtYWlsLmNvbSIsImlhdCI6MTYyNzIzMTMyNiwiZXhwIjoxNjI3MjQ5MzI2fQ.ZgSYNoD_OfZKNchPVWUL4z_pbJZUOH4DAoovP3OsKXFj7nnUtdhI1aTuGnzyDrLduLvDKxS2pduN5tHEHKO2bQ' \
--header 'Content-Type: application/json' \
--data-raw '{
    "code": "WT",
    "name": "WATCHES"
}'
```

Also we can create category list by apis
```
curl --location --request POST 'http://localhost:8080/ops/v1/categories/multiple' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aWVuLmRhbzIxNDE3QGdtYWlsLmNvbSIsImlhdCI6MTYyNzIzMTMyNiwiZXhwIjoxNjI3MjQ5MzI2fQ.ZgSYNoD_OfZKNchPVWUL4z_pbJZUOH4DAoovP3OsKXFj7nnUtdhI1aTuGnzyDrLduLvDKxS2pduN5tHEHKO2bQ' \
--header 'Content-Type: application/json' \
--data-raw '{
  "items": [
    {
      "code": "SH",
      "name": "SHOES"
    },
    {
      "code": "LB",
      "name": "Luggage & Bags"
    },
    {
      "code": "CO",
      "name": "Computer & Office"
    },
    {
      "code": "JC",
      "name": "Jackets & Coats"
    },
    {
      "code": "CE",
      "name": "Consumer Electronics"
    }
  ]
}'
```

- New we can create new Product
```
curl --location --request POST 'http://localhost:8080/ops/v1/products' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aWVuLmRhbzIxNDE3QGdtYWlsLmNvbSIsImlhdCI6MTYyNzIzMTMyNiwiZXhwIjoxNjI3MjQ5MzI2fQ.ZgSYNoD_OfZKNchPVWUL4z_pbJZUOH4DAoovP3OsKXFj7nnUtdhI1aTuGnzyDrLduLvDKxS2pduN5tHEHKO2bQ' \
--header 'Content-Type: application/json' \
--data-raw '{
    "category_id": 1,
    "brand_id": 1,
    "name": "Runway Quartz Mother Of Pearl Dial Ladies Watch",
    "colour": "Gold",
    "url": "https://www.watchshopping.com/media/catalog/product/cache/7881c480d497f569c4132299ea27b34f/M/i/Michael_Kors-Runway-MK6689.jpg",
    "price": 1500.00,
    "remain_amount": 100
}'
```

Also we can create product list by apis
```
curl --location --request POST 'http://localhost:8080/ops/v1/products/multiple' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aWVuLmRhbzIxNDE3QGdtYWlsLmNvbSIsImlhdCI6MTYyNzIzMTMyNiwiZXhwIjoxNjI3MjQ5MzI2fQ.ZgSYNoD_OfZKNchPVWUL4z_pbJZUOH4DAoovP3OsKXFj7nnUtdhI1aTuGnzyDrLduLvDKxS2pduN5tHEHKO2bQ' \
--header 'Content-Type: application/json' \
--data-raw '{
  "items": [
    {
      "category_id": 1,
      "brand_id": 1,
      "name": "Runway Quartz Mother Of Pearl Dial Ladies Watch",
      "colour": "Gold",
      "url": "https://www.watchshopping.com/media/catalog/product/cache/7881c480d497f569c4132299ea27b34f/M/i/Michael_Kors-Runway-MK6689.jpg",
      "price": 1500,
      "remain_amount": 172
    },
    {
      "category_id": 1,
      "brand_id": 2,
      "name": "2021 new Patek mechanical men'\''s and women'\''s steel belt belt watch, casual fashion Philippe luxury brand watch",
      "colour": "Gold",
      "url": "https://ae01.alicdn.com/kf/Had1ec669eb034901baa70a190ce0502dy.jpg?width=800&height=800&hash=1600",
      "price": 1500,
      "remain_amount": 552
    },
    {
      "category_id": 1,
      "brand_id": 3,
      "name": "Gold Silver Green Water Ghost Stainless Steel Top Brand Luxury Rolexable GMT Submariner Sport Waterproof Classic Men Watches",
      "colour": "Gold",
      "url": "https://ae04.alicdn.com/kf/H4ddfe80bfd0a46828488a3475e672e24r.jpg",
      "price": 1500,
      "remain_amount": 444
    },
    {
      "category_id": 1,
      "brand_id": 1,
      "name": "Runway Quartz Mother Of Pearl Dial Ladies Watch",
      "colour": "Gold",
      "url": "https://www.watchshopping.com/media/catalog/product/cache/7881c480d497f569c4132299ea27b34f/M/i/Michael_Kors-Runway-MK6689.jpg",
      "price": 1500,
      "remain_amount": 977
    },
    {
      "category_id": 1,
      "brand_id": 5,
      "name": "Rubber Strap Silicone Black Watch Band Men'\''s Wrist Belt for Omega Speedmaster Seamaster 300 Longines Accessories",
      "colour": "Black",
      "url": "https://ae01.alicdn.com/kf/H9b9d96df7e134613b8eea465d271bd56w.jpg",
      "price": 1500,
      "remain_amount": 453
    },
    {
      "category_id": 2,
      "brand_id": 7,
      "name": "Original New Arrival NIKE AIR MAX 270 REACT Men'\''s Running Shoes Sneakers",
      "colour": "White",
      "url": "https://ae01.alicdn.com/kf/Hc2605ad5388b4dc88b5f458bb29ea3c9G.jpg",
      "price": 1500,
      "remain_amount": 378
    },
    {
      "category_id": 2,
      "brand_id": 7,
      "name": "Original New Arrival NIKE COURT VISION MID Men'\''s Skateboarding Shoes Sneakers",
      "colour": "White",
      "url": "https://ae01.alicdn.com/kf/H22ca5a37c44443f7960499cfc4b851acw.jpg",
      "price": 1500,
      "remain_amount": 399
    },
    {
      "category_id": 2,
      "brand_id": 7,
      "name": "Original Nike Men '\''S Slippers Benassi Jdi 343880-016",
      "colour": "Black",
      "url": "https://ae01.alicdn.com/kf/U3dde173fb20b49ca89946f4fe1e1d3878.jpg",
      "price": 1500,
      "remain_amount": 176
    },
    {
      "category_id": 5,
      "brand_id": 7,
      "name": "Original New Arrival NIKE W NSW TEE ESSNTL LS ICON FTRA Women'\''s T-shirts Long sleeve Sportswear",
      "colour": "Black",
      "url": "https://ae01.alicdn.com/kf/Ha72ff75e82ee4ab7ba480c851453e8e15.jpg",
      "price": 1500,
      "remain_amount": 67
    },
    {
      "category_id": 5,
      "brand_id": 7,
      "name": "Original New Arrival NIKE AS M NSW SS TEE BTS FUTURA Men'\''s T-shirts short sleeve Sportswear",
      "colour": "White",
      "url": "https://ae01.alicdn.com/kf/H522e534010154010b5cb9457eb62ef99O.jpg",
      "price": 1500,
      "remain_amount": 3030
    },
    {
      "category_id": 4,
      "brand_id": 8,
      "name": "Matte Frosted Case Laptop Case for Apple MacBook Air Pro 13.3 15 13 12 Inch Case New Pro 13 A1932 A1989 Cover with Touch Bar",
      "colour": "Brown",
      "url": "https://ae01.alicdn.com/kf/H4e91775b9cb4499386320c7d09f8294fo.jpg?width=950&height=950&hash=1900",
      "price": 1500,
      "remain_amount": 877
    },
    {
      "category_id": 4,
      "brand_id": 8,
      "name": "iPhone 11 6.1\" Liquid Retina Display A13 Bionic Chip LTE 4G RAM 4G ROM 64GB/128GB Face ID Smartphones",
      "colour": "Blue",
      "url": "https://ae01.alicdn.com/kf/Hecad0bf30ae246459af6976ca0c6960dV.jpg",
      "price": 567.43,
      "remain_amount": 241
    },
    {
      "category_id": 4,
      "brand_id": 8,
      "name": "Apple iPhone XS Used (95% New)-original Hexa Core iOS Hexa-core Smartphone 4GB RAM 64GB 256GB ROM 5.8\"12MP 2160p LTE Phone",
      "colour": "Gold",
      "url": "https://ae01.alicdn.com/kf/H2bd779ed0d01418497bd0028b46d90b5o.jpg",
      "price": 686.9,
      "remain_amount": 333
    },
    {
      "category_id": 4,
      "brand_id": 8,
      "name": "iPhone 12 Pro / iPhone 12 Pro Max 5G Mobile Phones 6.1'\'''\'' / 6.7\" XDR Display A14 Chip 12MP Triple Camera Smartphone",
      "colour": "Blue",
      "url": "https://www.watchshopping.com/media/catalog/product/cache/7881c480d497f569c4132299ea27b34f/M/i/Michael_Kors-Runway-MK6689.jpg",
      "price": 1577.68,
      "remain_amount": 655
    },
    {
      "category_id": 3,
      "brand_id": 9,
      "name": "New Fashion Handmade Woven Bag Green Summer Shoulder Bag Lady Crossbody Hobo PU Knotted Handle Casual Handbag",
      "colour": "Red",
      "url": "https://ae01.alicdn.com/kf/Hcaf15c27e2b04f218cb6bf460d4897b8Q.jpg",
      "price": 20.3,
      "remain_amount": 349
    },
    {
      "category_id": 3,
      "brand_id": 9,
      "name": "Small Shoulder Bags Nylon Women Mobile Phone Bags Mini Female Messenger Purse Lady Wallet New 2021",
      "colour": "Gray",
      "url": "https://ae01.alicdn.com/kf/H99b35292f42e4e388d068133df11c9bfZ.jpg",
      "price": 4.63,
      "remain_amount": 1029
    },
    {
      "category_id": 3,
      "brand_id": 9,
      "name": "Luxurious diving fabric Neoprene breathable bag shoulder large-capacity brand Casual Tote bag Top-Handle Bags shoulder bags",
      "colour": "Orange",
      "url": "https://ae01.alicdn.com/kf/H40a46ab02aa2404ca9400a6568cb84e4Q.jpg",
      "price": 1500,
      "remain_amount": 902
    },
    {
      "category_id": 3,
      "brand_id": 9,
      "name": "2021 New Brands Designer Women Shoulder Bag Flap ladies leather Handbags Messenger Bag women Clutch Bag Swallow Buckle purse",
      "colour": "Brown",
      "url": "https://ae01.alicdn.com/kf/Hfd6bd434026c47099fdca24319ed894ab.jpg",
      "price": 21.34,
      "remain_amount": 3022
    },
    {
      "category_id": 3,
      "brand_id": 9,
      "name": "2021 New Fashion CH Women Handbags Unique Designer Shoulder Messenger Bags Large Capacity Casual Totes Women Purse and Handbags",
      "colour": "Green",
      "url": "https://ae01.alicdn.com/kf/H5a49e395cbb849478cb5050887ae1379j.jpg?width=960&height=960&hash=1920",
      "price": 53.6,
      "remain_amount": 2088
    },
    {
      "category_id": 3,
      "brand_id": 9,
      "name": "Geestock Women Wallets Long Purse Two Fold Women Wallets Drawstring Nubuck Leather Zipper Suede Women'\''s Long Design Purs",
      "colour": "Brown",
      "url": "https://ae01.alicdn.com/kf/Hb1e307884deb4820b2678c5894de6168e.jpg",
      "price": 15.94,
      "remain_amount": 644
    }
  ]
}'
```

Now data is ready save to database and store on Algolia. We can get product by filter as customer.

4. Create new Customer account
```
curl --location --request POST 'http://localhost:8080/api/v1/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "tien.dao21418@gmail.com",
    "password": "abcd123456",
    "name": "Tien Dao",
    "full_address": "1D Nguyen Hue, District 1, HCM",
    "phone_number": "0907958078"
}'
```

5. Login to JDC Public( public APIs for customer accept and buy product)
```
curl --location --request POST 'http://localhost:8080/api/v1/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "tien.dao21418@gmail.com",
    "password": "abcd123456"
}'
```

Example Response
```
{
    "meta": {
        "code": "200",
        "message": "Success"
    },
    "data": {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aWVuLmRhbzIxNDE4QGdtYWlsLmNvbSIsImlhdCI6MTYyNzIzMTc2MCwiZXhwIjoxNjI3MjQ5NzYwfQ.T01e9SeP7RbUrtcgqO-L3jkAr25Fs1EnejUFim-fpe-YP2JAnu2FSsR5vGD2a3eReV5NydmUuXs3e1A-MTTu8A"
    }
}
```

6. Now customer can search product by filter.

