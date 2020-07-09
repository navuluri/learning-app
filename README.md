# Learning App


An Upwork assignment to demonstrate REST API

## Index

* [Code Structure](#code-structure)
* [Data](#data)
* [Spring Framework Components](#spring-framework-components)
* [Open Source Libraries](#open-source-libraries)
* [Build](#build)
* [Javadocs](#javadocs)
* [Deploy](#deploy)
* [Application Server](#application-server)
* [Run](#run)
* [APIs](#apis)
* [Technology choice](#technology-choice)
* [Configuration](#configuration)



## Code Structure

The code in this assignment is arranged in the below folder structure

``` bash

├── com.learning.app          # The root package
    ├── api                   # This package contains all the APIs 
    ├── exceptions            # This package contains all the exceptions that arise in the application
    ├── models                # This package contains all the value objects
    ├── repository            # This package contains all the Data access 
    ├── service               # This package contains all the business logic
    └── utils                 # This package contains all the utility classes used for this assignment

```

Please refer to [Javadocs](#javadocs) section on generating a detailed explanation of the code

## Data

The sample data for this application is scraped (using [**Selenium**](https://www.selenium.dev/)) from [**Udemy**](https://www.udemy.com/courses/free/?p=1). The courses name, description and pricing is kept in a course.json file in resources folder 

## Spring Framework Components

* **Spring Boot** - The core library for bootstrapping the entire application (Uses Tomcat 9 as default) 
* **Spring Web** - For exposing REST Endpoints
* **Spring JDBC Template** - For Data access 

## Open Source Libraries

Apart from Spring Boot and other Spring dependencies, this assignment uses the below open source libraries 

* **Project Lombok** - Lombok is used to avoid boilerplate code like getters/setter/toString/hashCode/default constructors etc. More details about Project lombok can be found here:  [**Project Lombok**](https://projectlombok.org/)
* **H2** - The in-memory database. The H2 database can be used in different modes like File, In-Memory, TCP etc. I have choosen the In-Memory style database for faster access. The data is destroyed when the application is shutdown. The data is populated again during the application startup
* 

## Build

## Javadocs

## Deploy

## Application Server

Spring Boot 2.x by default comes with [**Apache Tomcat 9.x**](http://tomcat.apache.org/). So, for this App, the default Tomcat provided by the Spring Boot has been used (without any tuning) 

## Run

## APIs

## Technology choice

The technology stack used for this assignment has been chosen keeping in mind the below points 

* **Simple** - Since this is just an assignment used to develop a single API, I chose to keep the technology stack simple :smile: . For example, for data access, JPA could be used instead of JDBC Template  
* **Clean** - Wanted to keep the code clean and minimal. For example, to support **HATEOAS** it would have required an additional dependency and more code would have been required.
* **Faster Development** - Since this is a competition, the focus was to develop faster
* **Data source** - The datasource chosen for the assignment was a simple H2 database. For a real application we could choose a better In-Memory databases like [**Redis**](https://redis.io/). Also since the requirement is to keep the data dynamic with minimal changes to the user-interface, we can consider using a NOSQL database like [**MongoDB**](https://www.mongodb.com/)
 
## Configuration

The application configuration (**application.yml**) can be found in **resources** folder.
The file contains 2 profiles 
* Development
* Production

The default profile is **Development**. So all the configurations required for this profile have been configured and the configurations required for **Production** profile has been ignored. 
The configurations are self-explained. For more information, please refer Spring Configuration guide.
<p>Apart from Spring configurations, the below are the additional configurations that are used for the assignment </p>

``` yml
pricing:
  strategies: Free, One-Time, Subscription

```
  

The above configuration is created for the below requirement :

> There should be a global configuration that should be used in cases courses have not added their overrides.

So, to demonstrate the above requirement, if the course does not have a pricing strategy, I am filling the strategy with above three options  
