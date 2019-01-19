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

## Usage
All trails can be filtered by any of the available attributes. The list of available attributes are:
- AccessType
- AccessID
- Class
- Address
- Fee
- BikeRack
- BikeTrail
- DogTube
- Grills
- TrashCans
- ParkSpaces
- ADAsurface
- ADAtoilet
- ADAfishing
- ADAcamping
- ADApicnic
- ADAtrail
- ADAparking
- ADAfacilit
- ADAfacName
- HorseTrail
- DateFrom
- DateTo
- RecycleBin
- DogCompost
- AccessName
- THLeash

### Examples
To return all trails that have a bike rack and no fee, navigate to:
```
http://localhost:8080/trails?BikeRack=Yes&Fee=No
```
To return all bike trails, navigate to:
```
http://localhost:8080/trails?BikeTrail=Yes
```
To return all trails, navigate to:
```
http://localhost:8080/trails
```

Boulder's trail head data is [located here](https://bouldercolorado.gov/open-data/city-of-boulder-osmp-trailheads/) and there is an endpoint with a [CSV dump of the latest data here](http://www-static.bouldercolorado.gov/docs/opendata/OSMPTrailheads.csv).

# Design and Tradeoffs
## Web API
Chose a web API implementation for the following reasons:
- Ease of accessibility to others 
- Thick client application, while quick to develop, is not flexible and forces users to download the application
- Previous experience with developing web APIs 

## Spring Boot
I have not used Spring Boot prior to this project. Knowing that I want to utilize a Web API based solution, a lightweight web server had to be set up. It could be set up manually (time consuming, which is unacceptable). Seeing Spring Boot before in other projects, I knew it had a plethora of features that might solve my issue.

Hooking Spring Boot into this allowed me to get a basic web server and endpoint up and running in under 1 hour. That was awesome! 

## JSON
Chose to convert the trail data from CSV to JSON for several reasons:
- Less verbose than CSV
- Fast. Being less verbose equates to less data being transmitted across the wire
- Besides, much nicer to look at than CSV or even XML

# Improvements
Given the short time to complete this project, I had to prioritize the features I wanted to complete. After doing a brain dump, I sorted the features based on estimated complexity and business need. For example, needing the ability to access an endpoint that returned the trails was a higher priority compared to converting the CSV to JSON. Time permitted and I was able to complete the CSV to JSON conversion as well.

The following is a list of improvements that, given more time, would be addressed. They are not in a prioritized list.
- Create endpoint documentation using tool such as Swagger to generate API documentation
- Improve storage of and querying of the trails
- Allow for a user to “OR” trail attributes
- GUI that provides points on a map (i.e., using Google Maps) that show the location of the trails and clicking the point shows the details of the trail head
- Functionality that recommends trails based on your previous trail selections
- Integration tests
- Expanded unit tests for additional cases
- Address NOTES in comments in code

# Brain dump
Below is an unstructured brain dump I did when first designing this. It is included for historical purposes
- How to store a copy of the trail head data?
  - Do not want to continuously query endpoint
  - In memory cache
    - Gone if instance dies
    - Quick to do
    - Is this necessary?
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