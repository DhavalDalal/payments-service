# Getting Started

### To Start Dev Loop 
1. In one Terminal ==> ```gradle bootRun``` or to run on another port ```gradle bootRun -PjvmArgs="-Dserver.port=10001"```.  If ```-Dspring.profiles.active``` is not given, then the default, ```development``` profile is selected.
    * To run a different profile at start-up, use ```gradle bootRun -Dspring.profiles.active=jenkins```.  
2. In another Terminal ==> 
    * To reload latest classes in the JVM, use ```gradle compileJava```  
    * To reload latest changes in static HTML files, use ```gradle reload```  
3. In the gradle test mode ==> use ```gradle bootRun -Dspring.profiles.active=test```.  This will automatically use the HSQLDB In-memory implementation for running tests.  In the development profile, MYSQL is selected.
### To Debug
* Using only Intellij IDE
    * Debugging is as simple as navigating to the class with the main method, right-clicking the triangle icon, and choosing Debug.
* Using another JVM process and Intellij IDE
    1. In one Terminal ==> ```gradle bootRun -Dserver.port=10001 -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000```
        * Understanding the Java Debug Args - By default, the JVM does not enable debugging. This is because:
          * It is an additional overhead inside the JVM. 
          * It can potentially be a security concern for apps that are in public.
          Hence debugging is only done during development and never on production systems.
          
          Before attaching a debugger, we first configure the JVM to allow debugging. 
          We do this by setting a command line argument for the JVM:
          ```-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000```
          
          * ```-agentlib:jdwp``` - Enable the Java Debug Wire Protocol (JDWP) agent inside the JVM. This is the main command line argument that enables debugging.
          * ```transport=dt_socket``` - Use a network socket for debug connections. Other options include Unix sockets and shared memory.
          * ```server=y``` - Listen for incoming debugger connections. When set to n, the process will try to connect to a debugger instead of waiting for incoming connections. Additional arguments are required when this is set to n.
          * ```suspend=n``` - Do not wait for a debug connection at startup. The application will start and run normally until a debugger is attached. When set to y, the process will not start until a debugger is attached.
          * ```address=8000``` - The network port that the JVM will listen for debug connections. Remember, this should be a port that is not already in use.
    2. On Intellij IDE ==> 
        * Open menu Run | Edit Configurations...
        * Click the + button and Select 'Remote' from Templates

## Test Pyramid
* Unit Tests
* Integration Tests
* Component Tests
* Contract Tests
* End-To-End Tests

### Unit Tests
* Domain-level Unit Tests
* Service Layer Unit Tests
* Web-layer (Controller) Unit Tests  

### Integration Tests


### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.0-M3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.0-M3/gradle-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

