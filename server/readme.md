# Server
Server side of application allowing calculating monthly net earnings in PLN.
This module provides two endpoints described further below.
Port assigned to this server is 8080.

## Technology
This module was build with use of Java 11 and Spring Boot 2.1.0.

## Build
Prerequisite for running this module is having Maven installed.
For installation please type in
```
mvn clean install
```

## Running
Once installation is done you can run server by typing
```
mvm spring-boot:run
```

## Endpoints
Server provides endpoint allowing fetching countries supported by its main service at
```
http://localhost:8080/countries
```

Main service enabling calculation of monthly net earnings in PLN is available at
```
http://localhost:8080/earnings/{countryCode}/{grossDailyEarnings}
```

Where countryCode stands for ISO 3166-1 code of supported country and grossDailyEarnings should be supplied in currency relevant for provided country code.
