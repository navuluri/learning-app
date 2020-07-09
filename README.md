# Learning App


An Upwork assignment to demonstrate REST API

## Index

* [Code Structure](#code-structure)
* [Data](#data)
* [Spring Framework Components](#spring-framework-components)
* [Open Source Libraries](#open-source-libraries)
* [Build](#build)
* [Javadocs](#javadocs)
* [Application Server](#application-server)
* [Run](#run)
* [APIs](#apis)
* [REST Console](#rest-console) 
* [cURL](#curl)
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
* **H2** - The in-memory database. The H2 database can be used in different modes like File, In-Memory, TCP etc. I have chosen the In-Memory style database for faster access. The data is destroyed when the application is shutdown. The data is populated again during the application startup
* **commons-io** - The Apache commons IO library has been used to read the course.json file and other file operations
* **Springfox Swagger2 and springfox-swagger-ui** - These libraries are used to generate the Swagger documentation of the APIs

## Build

To build the project follow the below instructions. Go to the root of the application and give the command:

```bash

mvn clean package

```

After successful execution, there will be a folder called **target** at the root of the project. The target folder contains the executable jar **learning-app.jar**

> As part of the build's package goal, a plugin to generate javadocs for the assignment runs. This plugin generates Javadocs for the entire application code. Refer [Javadocs](#javadocs) section for more information  

## Javadocs

After successful build, in the **target** folder, there will be a folder called **apidocs**. 
This apidocs folder contains the Javadocs in HTML format. Click on **index.html** (that is available in the apidocs folder) to navigate through the Javadocs

## Application Server

Spring Boot 2.x by default comes with [**Apache Tomcat 9.x**](http://tomcat.apache.org/). So, for this App, the default Tomcat provided by the Spring Boot has been used (without any tuning) 

## Run

To start the application follow the below instructions

* Go to **target** folder
* Issue the command :

```bash
java -Dspring.config.location=<LOCATION_OF_application.yml>-jar learning-app.jar

Example: java -Dspring.config.location=C:/application.yml-jar learning-app.jar 
```

By default, the application starts on the port specified in the application.yml file (which is 18080).

> Note the default port can be overridden using the options -Dserver.port

To start the server on a different port 

```bash
java -Dserver.port=XXXXX  -Dspring.config.location=<LOCATION_OF_application.yml>-jar learning-app.jar
Example: java -Dserver.port=19090  -Dspring.config.location=C:/applicatiton.yml -jar learning-app.jar
```
## APIs

The supported APIS are : 

* **getCourse** - **HTTP GET** - Get course details for a given Course Id
* **getAllCourses** - **HTTP GET** - Get all the avaialble course details
* **createCourse** - **HTTP POST** - Create a new course
* **deleteCourse** - **HTTP DELETE** - Delete a course 

For the payloads and the URL, Check [**REST Console**](#rest-console) section below

## REST Console 

The APIs are [**Swagger-Enabled**](https://swagger.io/). That is, the APIs can be tested and the API documentation can be read online. To read and test the APIs using Swagger console follow the below instructions :

* Start the server as mentioned in [Run](#run) section
* Open a browser to [http://host:port/swagger-ui](http://host:port/swagger-ui). Example : [http://localhost:18080/swagger-ui](http://localhost:18080/swagger-ui)
* If everything is OK, you have a screenshot similar to :
  ![Swagger API Documentation](/screenshot/swagger1.png?raw=true "Swagger APIs")
  ![Swagger API Test Console](/screenshot/swagger2.png?raw=true "Swagger APIs")

## cURL

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
