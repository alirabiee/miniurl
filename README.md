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
```
mvn package
docker run -d -p 8080:8080 miniurl
```

It will be accessible on localhost:8080.
