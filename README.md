# jdc
JAVA DEVELOPMENT CHALLENGE


### Directory layout

```
.
├── src                                         # Source files
  ├── main
    ├── java.jdc.onlineshopping                   # OD Common + OD Public root pakage

      ├── config                                # Configuration folder
      ├── domain                                # Entities folder
      ├── repository                            # Repository folder
      ├── service                               # Common service folder
        ├── dto                                 # Data Transfer Object Folder
        └── mapper                              # Mapper for the entity and its DTO              # Resource/Controller
    └── OnlineShoppingApplication.java          # Application Main Class
    └──  resources                              # Resource folder

    ├── java.jdc.onlineshopping.api               # OD Internal root package
        ├── service                             # Service folder
        ├── web.rest                                 # Data Transfer Object Folder
        └── mapper                              # Mapper for the entity and its DTO
        ├── web/rest
    
  └──  test                       # Folder test
├── target                        # Build folder
├── pom.xml                       # Project Object Model
└── README.md

```