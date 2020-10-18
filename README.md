# About the app

Simple message board web application

Receives message in the request and persists in the application

* title
* content
* sender
* url (valid url)

v1
* returns only title, content and sender
* does not accept more parameters than version, always returns JSON
v2
* returns all 4 fields
* supports formats JSON / XML

# Requirements / Stack :

* Maven
* Java 11
* Jersey
* Spring boot

# Included in the zip

### Scripts
* Happy scenarios
* Unhappy scenarios
* Build and run

# Build / Run from commandline

From project root:

* run only unit tests: ```mvn test```
* complete build: ```mvn clean install```
* build without unit tests: ```mvn clean install -DskipTests```
* run jar: ```mvn spring-boot:run```

After running, execute any happy/unhappy script or perform your own curl.