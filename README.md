# Dependencies
### MongoDB Compass (Version 1.39.1)
- GUI for MongoDB
- MongoDB is a NoSQL database
- Download: https://www.mongodb.com/try/download/compass

### Docker Desktop
- Application for building and running containerized applications and microservices
- Required to run MongoDB Compass
- Download: https://www.docker.com/products/docker-desktop/

# Running the application
### Step 1: Start Docker
- Open docker desktop and verify it is running

### Step 2: Start MongoDB
- Pull the Docker image: `docker pull mongo`
- Run the container: `docker run -d -p 27017:27017 --name mongodb mongo`
- Check if the container is running: `docker ps`
- Open MongoDB Compass
- Set the uri to `mongodb://localhost:27017/` and hit connect

### Step 3: Start the application
- Clone the repository: `https://github.com/RomanLachenmayr/micronaut-kotlin-challenge.git`
- Open your IDE and create a new project with this repository 
- Once the project is built, navigate to `src` > `main` > `kotlin` > `OnlineMarketplace` and run the main method in the `Application.kt` file
- Verify that `http://localhost:8080` is displayed in the console
- The application is running now

# Running the tests 
- Firstly, perform the three steps listed above
- In Order to run all tests, navigate to `src` > `test` > `kotlin` > `OnlineMarketplace` and run the class `MarketplaceTest`
- In Order to run a single test, click the play button next to the desired test. Note that test 5 must be slightly adjusted to be run independently


# Micronaut 4.0.4 Documentation

- [User Guide](https://docs.micronaut.io/4.0.4/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.0.4/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.0.4/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)
- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
# Feature test-resources documentation

- [Micronaut Test Resources documentation](https://micronaut-projects.github.io/micronaut-test-resources/latest/guide/)


# Feature data-mongodb documentation

- [Micronaut Data MongoDB documentation](https://micronaut-projects.github.io/micronaut-data/latest/guide/#mongo)

- [https://docs.mongodb.com](https://docs.mongodb.com)


# Feature ksp documentation

- [Micronaut Kotlin Symbol Processing (KSP) documentation](https://docs.micronaut.io/latest/guide/#kotlin)

- [https://kotlinlang.org/docs/ksp-overview.html](https://kotlinlang.org/docs/ksp-overview.html)


# Feature micronaut-aot documentation

- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)


# Feature serialization-jackson documentation

- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)


# Feature mongo-sync documentation

- [Micronaut MongoDB Synchronous Driver documentation](https://micronaut-projects.github.io/micronaut-mongodb/latest/guide/index.html)

- [https://docs.mongodb.com](https://docs.mongodb.com)


