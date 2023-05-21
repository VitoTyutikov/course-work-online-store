# Online shop course work

This repository contains the source code of an my course work - online electronics shop, a web application developed using Java and the Spring framework.

## About the Project

The online electronics store is a platform where users can browse and purchase electronic products. It includes functionality for user registration, order processing, and interaction with real customers through a review and comment system.

## Technologies

The project utilizes the following technologies and tools:

• Spring Boot: A framework that simplifies Java application development by providing essential components such as dependency management, configuration, and an embedded server.

• Spring Data JPA and Hibernate: Used for working with the database. Spring Data JPA facilitates database interaction through object-relational mapping (ORM), and Hibernate is one of the most popular JPA implementations.

• Spring Security: A framework for securing web applications. In the project, it is used for user authentication and authorization.

• JavaScript: Used for developing the client-side of the application, including user interaction and event handling.

• PostgreSQL: A relational database used for storing information about products, users, and other entities related to the electronics store.

• Postman: An API testing tool that allows sending HTTP requests to the application and verifying the responses.

## Running the Project

To run the project locally, follow these steps:

1. Ensure that Java and PostgreSQL are installed on your system.
2. Clone the repository to your local machine.
3. Create a PostgreSQL database and configure the corresponding connection parameters in the application.properties file.
4. Build the project using Maven: mvn clean install.
5. Run the application: mvn spring-boot:run.
6. Open a web browser and navigate to http://localhost:8080 to access the online electronics store.
