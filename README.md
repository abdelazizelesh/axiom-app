Axiom PoC
-------------------
This PoC is designed to provide a search API (GET /mobile/search?) that will allow the caller to retrieve one or more mobile handset record based on the passed search criteria.

The criteria can be any field in the handset data along with any value. 
Examples:
  - /mobile/search?priceEur=200
  - /mobile/search?sim=eSim
  - /mobile/search?announceDate=1999&price=200

Assumptions
-------------------
The data in “Mobile handset” database is static and doesn’t get updated.

Architecture
-------------------
![Architecture](https://i.imgur.com/C2xYWo4.png)
### The solution as depicted is divided into below layers:

  - RestController is the presentation layer that receives the HTTP request.
  - Service is the business layer that handles all the business logic.
  - CacheManager is the cashing layer that stores data in memory to provide low latency and enhance the performance.
  - RestTemplate is the data access layer that sends http requests to rest services endpoints.

 ### Request flow:

 - The client calls the search API (GET /mobile/search?) with optional query string parameters.
 - The controller receives the request, processes the parameters and call the service method.
 - The service method calls the cache manager to retrieve the json data, If data not exists in the cache manager, it sends http request to mobile handset rest service and store the data in the cache, then filter the data and return filtered list of devices.
 - The filtered list of devices is returned to the clint if no error occured.
 
Technology Stack
-------------------
  - Spring Boot 2.2.5.RELEASE
  - Spring 5.2.4.RELEASE
  - Json Path 2.4.0
  - junit 4.12
  - Maven 3
  - Tomcat Embed 9.0.31
  - Java 8

Get the code:
-------------------
Clone the repository:
     
	 $ git clone https://github.com/abdelazizelesh/axiom-app.git

Run the application from Spring boot 
-------------------

       $ cd axiom-app
       $ ./mvnw clean package
       $ ./mvnw spring-boot:run
       
Run the application from Spring boot Docker image
-------------------

	$ cd axiom-app
	$ ./mvnw clean package
    $ docker build -t axiom-app:1.0 .
    $ docker run -p 8080:8080 -t axiom-app:1.0

Access the application:
-------------------

Access the deployed web application at: http://localhost:8080/mobile/search