# Learning App


An Upwork assignment to demonstrate REST API

### Index

* [Code Structure](#code-structure)
* [Data](#data)
* [Spring Framework Components](#spring-framework-components)
*  


### Code Structure

### Data

The sample data for this application is scraped (using Selenium) from [Udemy](https://www.udemy.com/courses/free/?p=1). The courses name, description and pricing is kept in a course.json file in resources folder 
 

### Spring Framework Components

* **Spring Boot** - The core library for bootstrapping the entire application (Uses Tomcat 9 as default) 
* **Spring Web** - For exposing REST Endpoints
* **Spring JDBC Template** - For Data access 

### Open Source Libraries

Apart from Spring Boot and other Spring dependencies, this assignment uses the below open source libraries 

* **Project Lombok** - Lombok is used to avoid boilerplate code like getters/setter/toString/hashCode/default constructors etc. More details about Project lombok can be found here:  [Project Lombok](https://projectlombok.org/)
* **H2** - The in-memory database. The H2 database can be used in different modes like File, In-Memory, TCP etc. I have choosen the In-Memory style database for faster access. The data is destroyed when the application is shutdown. The data is populated again during the application startup
* **Spring Boot**
* **Spring MVC**
* **Spring Data (JDBC Template)**
* **MySQL**
* **Tomcat 8**

### Build

### Javadocs

### Deploy

### Invoke / Test

### Reason for choosing the stack

### Configuration



