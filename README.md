# miniurl
Simple URL Shortener

# Description
This is a web application providing the simplest interface to shorten arbitrary URLs.

# Pre-requisites
* JDK 8
* Maven 3.x
* Docker Engine

# Features
* Shortening arbitrary URLs
* Soft duplicate prevention
* Stateless
* Simple UI
* reCAPTCHA-enabled, hence CSRF protected
* XSS free
* In-memory database (must be changed for production)
* Dockerized
* Functionality tests included
* Load tests included

# How to run all tests
```
mvn verify
```

# How to build and start the container
Before you begin, make sure you have specified the correct keys for reCAPTCHA in [application.properties](src/main/resources/application.properties). The default key only works for _localhost_ domain.
Then you can build and start the container like this:

```
mvn package
docker run -d -p 8080:8080 miniurl
```

It will be accessible on _localhost:8080_.
