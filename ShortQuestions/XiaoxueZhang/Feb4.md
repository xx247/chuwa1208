2. What is Aspect Oriented Programming, what does "aspect" mean? Explain its use cases in detail?
Aspect-Oriented Programming (AOP) is a programming paradigm that allows you to separate cross-cutting concerns from business logic.
It lets you define behavior that should apply across many parts of an application without duplicating code.
Logging

Without AOP:

public void createUser() {
log.info("Method started");
// business logic
log.info("Method finished");
}

In this approach, logging statements are repeated in every method where logging is required, leading to duplicated code across the application.

With AOP:

@Around("execution(* com.example.service..(..))")
public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
log.info("Start");
Object result = joinPoint.proceed();
log.info("End");
return result;
}

With AOP, the logging logic is defined once in an aspect and automatically applied to all matching service methods. This removes duplication and keeps business logic clean.

Transaction Management (Major Use Case)

Spring’s @Transactional is implemented using AOP.

When you write:

@Transactional
public void saveUser() {
}

Spring automatically:

Starts a transaction before the method executes

Commits the transaction if the method completes successfully

Rolls back the transaction if an exception occurs

All of this behavior is applied through an AOP proxy without adding transaction code inside the business method.

Security Checks

Example:

@PreAuthorize("hasRole('ADMIN')")

Spring Security uses AOP to:

Intercept the method before execution

Check whether the current user has the required role

Block method execution if the authorization check fails

This allows security logic to be separated from business logic.

Performance Monitoring

Measure execution time:

@Around("execution(* com.example..*(..))")
public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
long start = System.currentTimeMillis();
Object result = pjp.proceed();
long end = System.currentTimeMillis();
System.out.println("Execution time: " + (end - start));
return result;
}

This approach is useful for:

Profiling application performance

Collecting performance metrics

Monitoring slow APIs

The performance measurement logic is centralized and reusable across the application.

Exception Handling

Centralize error logging:

@AfterThrowing(pointcut = "...", throwing = "ex")
public void logException(Exception ex) {
log.error("Exception occurred", ex);
}

This allows consistent exception logging across multiple layers without embedding logging code in each method.

Caching

Spring’s @Cacheable uses AOP.

Before the method executes:

Check if the result exists in the cache

If it exists, return the cached value

If it does not exist, execute the method and store the result in the cache

All of this is handled automatically through a proxy, without modifying the business method itself.

3. What are the advantages and disadvantages of Spring AOP?
Advantages:

Clean separation of concerns

Reduces code duplication

Enables declarative features (@Transactional, @Cacheable)

Improves maintainability

Easy to integrate with Spring

Disadvantages:

Proxy-based limitations

Self-invocation issue

Only works on Spring-managed beans

Slight performance overhead

Harder debugging

Less powerful than AspectJ
4. Compare Spring AOP vs Java Reflection vs Spring Interceptor.
Spring AOP is used for cross-cutting concerns at the method level using proxies. Java Reflection allows runtime inspection and dynamic invocation of classes and methods but does not provide automatic interception. Spring Interceptor works at the web layer to intercept HTTP requests before and after controller execution. In short, AOP handles business-layer cross-cutting logic, Interceptor handles web requests, and Reflection is a low-level runtime inspection mechanism.

5. Explain following concept in your own words, you may include code snippet as part of your answer.
1. Aspect A reusable module that applies behavior across multiple classes
2. PointCut A filter that chooses which methods should be intercepted.
3. JoinPoint A JoinPoint represents a specific point during execution where an aspect can be applied.
4. Advice An Advice is the action that runs at a join point.
It defines what should happen and when it should happen.

6. How do we declare a pointcut, can we declare it without annotating an empty method?
Name some expressions to do it.
A pointcut can be declared either using the @Pointcut annotation on an empty method or directly inline inside advice annotations. The empty method is optional and mainly used for reusability and readability. Common pointcut expressions include execution(), within(), @annotation(), @within(), args(), and bean(). These expressions can also be combined using logical operators like &&, ||, and !.

7. Compare different types of advices in Spring AOP.
| Advice Type     | Runs Before | Runs After | On Success | On Exception | Can Modify Return | Can Prevent Execution |
| --------------- | ----------- | ---------- | ---------- | ------------ | ----------------- | --------------------- |
| @Before         | Yes         | No         | N/A        | N/A          | No                | Yes (throw exception) |
| @After          | No          | Yes        | Yes        | Yes          | No                | No                    |
| @AfterReturning | No          | Yes        | Yes        | No           | Limited           | No                    |
| @AfterThrowing  | No          | Yes        | No         | Yes          | No                | No                    |
| @Around         | Yes         | Yes        | Yes        | Yes          | Yes               | Yes                   |

8. On top of your Spring application (you may create a new one or use your previous project),
1. Implement a customized logger using Spring AOP, your logger should be able to log
your code and also external code.
2. Your AOP logger should log method execution time, Rest API request details and
response details.
3. Your AOP logger should log with all possible joint points (before method execution,
after method execution etc...)
4. Your should bind jointPoints with your AOP code directly, instead of binding it with an empty method.
5. Be ready to demo your implementation and prove it works in class.