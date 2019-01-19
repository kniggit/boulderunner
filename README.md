# Boulder Runner

![Boulder Runner](https://i.imgur.com/CEnC4ha.gif)
Boulder Runner serves as an easy to use API to query the City of Boulder for its hiking trails. Filtering of trails based on different features such as the number of barbecue grills or difficulty of the trails is possible. How to get started and examples are below.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The following must be available on your system in order to open, modify, compile, and test the available code.

- [Eclipse](https://www.eclipse.org/) or a compatible IDE
- [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)

To only execute the pre-compiled jar, [JRE 11](https://www.oracle.com/technetwork/java/javase/downloads/index.html) must be installed.

### Installing

Clone this repo to your machine using:
```bash
git clone https://github.com/kniggit/boulderunner.git
```
Run maven to download any dependencies and compile the application:
```bash
mvn clean compile
```

### Running
#### Linux
To execute the Boulder Runner microservice in a Linux environment, execute either of the following:
```bash
mvn spring-boot:run
```
or
```bash
java -jar target/BoulderTrails-0.0.1-SNAPSHOT.jar
```
#### Windows
To execute the Boulder Runner microservice in a Windows environment, double click on the following file:
```
BoulderTrails-0.0.1-SNAPSHOT.jar
```

## Running the tests
Unit tests are included as part of this repository. They can be executed with:
```bash
mvn test
```






Boulder's trail head data is [located here](https://bouldercolorado.gov/open-data/city-of-boulder-osmp-trailheads/) and there is an endpoint with a [CSV dump of the latest data here](http://www-static.bouldercolorado.gov/docs/opendata/OSMPTrailheads.csv). We've included a [copy of this data](./BoulderTrailHeads.csv) in this repo as well.

# Braindump
A brain dump of ideas to solve this problem:
- How to store a copy of the trail head data?
  - Do not want to continuously query endpoint
  - In memory cache
    - Gone if instance dies
    - Quick to do
    - Is this necessary if end goal is external storage?
      - Why not a mapping?
  - External storage
    - "Permanent"
    - Need to set up DB, etc..
    - Feature in the future, need a walking skeleton now
  - Store in what format?
    - XML
      - No
    - JSON
      - Convert .csv to JSON objects
- How to query the data?
   - Microservice
     - Web API
       - GET
         - Parameters can be any of the attributes defined in the .csv
         - Query the backend data store for matching results
         - Return as JSON
       - Need to set up Jetty/Netty, something lightweight
     - Thick client
       - Quick to get running
- Unit tests
  - JUnit
- Visualization
  - Time permitting, use something like Google Maps