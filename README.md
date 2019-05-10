# Validity 2
Project for validity full-stack developer role

**Features provided in this application :**

1. All points covered from problem statement
2. Application supported by Swagger2 Api, all documented URLs can be found in swagger url.
3. Docker implementation supported. More information about commands provided below. 
4. Acuator implementation supported, this can be used to check open ended URLs for health and metrics
5. Response in format of JSON, contained separated structure for both unique and possible duplicate records in the file.
    Also contains redundancy rate, which a metric of redundancy of the entire file. 

**Requirements**

For building and running the application you need:

    JDK 1.8
    Maven 3

**Running the application locally**

There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the main method in the com.validity.ProApplication class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

mvn spring-boot:run

**Deploying the application using Docker**

Requirements 

    Locally installed docker (Hyper V for window 10)
    
Use Docker file within the file structure to run the project:

    docker build -t validity-app .
    
    docker run -p 8080:8080 -name validity validity-app

This should run the application on 0.0.0.0:8080

Open Urls:
1. Swagger :
    
    `/swagger-ui.html`

2. Status :

    `/`

3. Health : 

    `/health`

4. File upload and other utilities :

    `/upload`
