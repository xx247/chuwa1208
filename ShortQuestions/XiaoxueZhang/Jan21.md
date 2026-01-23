1. Add newly learned annotations to your previous cheatsheet, add explainations for these annotations.
@Bean
Declares a Spring-managed bean explicitly by returning an object from a method.
@Componet
Marks a class as a Spring-managed component discovered by component scanning.
@Service
A specialized @Component that represents the business/service layer.
@Controller
Marks a class as a Spring MVC controller that handles HTTP requests and returns views.
@Repository
Marks a class as a data access layer component.
@ExceptionHandler
Handles specific exceptions thrown by controller methods.
@ControllerAdvice
Provides global exception handling and cross-cutting logic for controllers.
@valid
Triggers Bean Validation on an object (DTO/entity).
@NotEmpty
A validation constraint ensuring a field is not null and not empty.
2. Walkthrough sample codes under https://github.com/CTYue/springboot-redbook/commits/06_mapper-ex
ception, you are supposed to bring up the application on your local.
3. Explain why do we need model mappers in Spring, and in what scanrios we need it.
Model mappers are used to convert between entities and DTOs to separate persistence models from API models and improve maintainability, security, and performance.
Model mappers should be used in applications with multiple DTOs or complex mappings to reduce boilerplate and maintain clean architecture.

4. Provide 3 examples in which model mapper will NOT map succesfully, explain why.
Different field names (name mismatch): ModelMapper maps fields by name by default.
Lazy-loaded JPA relationships: ModelMapper tries to access post.getComments(), Hibernate cannot load it
Nested / complex structure mismatch: ModelMapper does not flatten nested objects automatically.

5. Explain how model mapper cast different data types between source object and target class.
ModelMapper converts different data types using built-in converters for compatible types and falls back to custom converters when automatic type casting is not possible.

6. Add your own API exceptions so that when something wrong happens in service layer, your rest API will return your customized response and status code.

7. Explain how Controller Advices work, is there any other approach to do same/similar global API exception handling?
A request hits a controller endpoint.
-> Your controller/service throws an exception (e.g., ResourceNotFoundException).
-> Spring’s DispatcherServlet consults a chain of HandlerExceptionResolvers.
-> If you have a matching @ExceptionHandler method in a @ControllerAdvice, Spring calls it.
-> That handler returns a ResponseEntity (status + body), and Spring writes it as the HTTP response.
Other approach:
Implement HandlerExceptionResolver
Extend ResponseEntityExceptionHandler

8. What's the difference between throwing a regular exception and a customized API exception that will be
eventually thrown to Controller Advice codes? Please provide screenshots to explain your findings.
9. Write some regular expression to restrict the value of attributes that your Post or Comment can have. You
may use https://regex101.com/ to construct and test/validate your regular expression.
10. Explain Spring framework fundamental principles. And how can they help build business applications?
Spring is a lightweight, modular framework that simplifies building enterprise Java applications by managing object creation, dependencies, and cross-cutting concerns.
The Spring Framework is based on core principles such as Inversion of Control, Dependency Injection, Aspect-Oriented Programming, and abstraction over infrastructure. These principles promote loose coupling, separation of concerns, and modular design, allowing developers to build scalable, maintainable, and testable business applications efficiently.

11. Explain different types of dependency injection, explain their suitable use cases, and why fielde injection
is not recommended in general. Please provide necessary code snippets and screenshots if possible.
Constructor injection: Dependencies are provided through the class constructor.
Setter injection: Spring injects dependencies through setter methods.
Field injection: Spring injects directly into fields via reflection.
You can’t tell what the class needs just by looking at the constructor.
In plain unit tests, you can’t pass mocks via constructor.

12. Explain different types of application context in Spring framework, with screenshots. You may take https://
github.com/CTYue/springIOC for reference.
13. Compare @Component and @Bean and in which scenario they should be used.
@Component Marks a class as a Spring-managed bean discovered by component scanning.
@Bean Registers the return value of a method as a bean in the Spring container, usually inside a @Configuration class.

14. Explain Spring bean scopes and how to pick the correct bean scope.
Bean scope determines the lifecycle and visibility of a bean inside the Spring container.
Use singleton when:
Bean is stateless thread-safe Shared across app Default choice (90% of cases)
Use prototype when:
Bean holds per-use state You want a new instance each time Lifecycle after creation is not managed by Spring
Use request when:
Data belongs to a single HTTP request
Use session when:
Data belongs to a logged-in user session

15. Explain the difference between bean id and bean class.
Bean Class: The Java class used to create the bean instance.
Bean ID: The unique identifier Spring uses to register and look up a bean in the container

16. Explain that when a bean has multiple alternative implementations, how will Spring decide which bean
implementation to inject/autowire?
When multiple beans implement the same interface, Spring resolves injection using @Qualifier, @Primary, or bean name matching; otherwise, it throws a NoUniqueBeanDefinitionException.

