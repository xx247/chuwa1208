1. List all of the annotations you learned from this class session.
2. Explain tight coupling vs loose coupling and what does Spring IOC do?
Tight coupling means a class is directly dependent on a concrete implementation, making changes difficult.
Loose coupling means a class depends on an abstraction (interface), not a concrete implementation.
Inversion of Control means the control of object creation and dependency management is moved from the application code to the Spring container.
Spring IoC:
Injects dependencies by interface
Hides concrete implementations
Allows easy replacement
3. What is MVC pattern?
MVC = Model – View – Controller
It is a separation-of-concerns design pattern that divides an application into three interconnected components so each part has one clear responsibility.
Model: Application data, Business rules, Domain objects
Controller: Handles incoming requests, Validates input, Calls service layer, Returns response or view
View: UI layer, Displays data to users
4. What is Front-Controller?
A Front Controller is a centralized controller that handles all incoming requests and delegates them to appropriate handlers, enabling centralized request processing and cross-cutting concern management.

5. Explain DispatcherServlet and how it works.
DispatcherServlet is the Front Controller of Spring MVC.
It receives all incoming HTTP requests, coordinates request processing, and dispatches them to the appropriate controller.

6. What is JSP and What is Model And View？
JSP is a server-side view technology used to generate dynamic HTML pages using Java.

Model represents application data and business objects that are passed from the controller to the view.

View is responsible for presenting the model data to the user in a readable format.

7. Explain servlet and servlet container , name some servlet implementations and servlet containers
other than tomcat
A servlet is a Java class that handles HTTP requests and responses on the server side.

A servlet container is a runtime environment that manages servlet lifecycle, request handling, and communication with clients.

Jetty, Undertow, GlassFish, WildFly, WebLogic, and WebSphere

8. clone this repo, and run it on you local,
1. https://github.com/CTYue/springmvc5-demo
2. Notice that you need to configure the Tomcat by yourself.
3. find out the APIs in controlelr and call some APIs, In slides, I also list some API.
4. remeber to setup mysql database for this project
5. Test APIs (controllers) in postman