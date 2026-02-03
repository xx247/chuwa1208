1. List all of the annotations you learned from class and homework to annotaitons.md
2. Explain TLS, PKI, certificate, public key, private key, and signature.
TLS is a protocol that provides secure communication over a network.
PKI is the system that manages keys, certificates, and trust.
A certificate is a digital ID card for a server (or user).
Domain name (e.g. example.com)
Public key
Issuer (CA)
Validity period
Digital signature of CA
A public key is a key that can be shared with everyone.
Used for:
Encrypting data
Verifying digital signatures
A private key is a secret key known only to the owner.
Used for:
Decrypting data
Creating digital signatures
A digital signature proves who sent the data and that it was not modified

3. Write a Spring security based application, which provides https APIs (one simple get controller with empty response is good enough )instead of http, please generate a self-signed certificate to make your https TLS verfication work.
1. Pack your self-signed certificate in the form of jks file, as part of your application, name it properly

2. Test if you can verify your HTTPs api without importing the self-signed certificate to your local certificate chain, if not, explain why.
3. Explain what did you do to make https call work, do NOT bypass TLS/SSL verfication in Postman (this
is cheating)!
Tutorial: https://www.baeldung.com/spring-channel-security-https
4. list all http status codes that related to authentication and authorization failures.
| Status Code | Meaning                    | Auth Type      |
| ----------- | -------------------------- | -------------- |
| **401**     | Not authenticated          | Authentication |
| **403**     | Not authorized             | Authorization  |
| **400**     | Invalid auth data          | Authentication |
| **404**     | Resource hidden            | Authorization  |
| **405**     | Method blocked             | Authorization  |
| **409**     | Auth conflict              | Authentication |
| **412**     | Missing security condition | Authorization  |
| **429**     | Too many auth attempts     | Authentication |

5. Compare authentication and authorization? Name and explain important components in Spring security that undertake authentication and authorization
Authentication = “Who are you?”
Verifies the identity of the caller (user/service).
Authorization = “What are you allowed to do?”
Checks permissions/roles after identity is known (or sometimes for anonymous users).
1. SecurityFilterChain

Entry point of Spring Security in web apps.

Defines which security filters apply to incoming HTTP requests.

Configured using HttpSecurity.

It intercepts every request before it reaches your controllers.

2. Authentication

Core interface representing the authenticated user.

Contains:

principal (user identity)

credentials (password/token)

authorities (roles/permissions)

authenticated flag

After successful login, an authenticated Authentication object is stored.

3. SecurityContext

Holds the current Authentication object.

Represents security information for the current request.

4. SecurityContextHolder

Stores the SecurityContext.

Default strategy: ThreadLocal (per request thread).

This allows access anywhere:

Authentication auth = SecurityContextHolder.getContext().getAuthentication();

5. AuthenticationManager

Main interface responsible for authenticating.

Takes an unauthenticated Authentication

Returns a fully authenticated one (or throws exception)

6. ProviderManager

Default implementation of AuthenticationManager.

Delegates authentication to one or more AuthenticationProviders.

7. AuthenticationProvider

Performs actual authentication logic.

Examples:

DaoAuthenticationProvider (username/password + DB)

JwtAuthenticationProvider

LdapAuthenticationProvider

8. UserDetailsService

Loads user data from database.

Returns UserDetails object.

UserDetails loadUserByUsername(String username)

9. UserDetails

Represents user information:

username

password

authorities

account status flags

10. PasswordEncoder

Secure password hashing and verification.

Example:

BCryptPasswordEncoder

11. Authentication Filters (Very Important)

These trigger authentication:

UsernamePasswordAuthenticationFilter

BasicAuthenticationFilter

BearerTokenAuthenticationFilter

They:

Extract credentials from request

Create Authentication

Pass to AuthenticationManager
1. AuthorizationFilter

Performs access control for HTTP requests.

Checks if the authenticated user has required authority.

2. AuthorizationManager (Modern Approach)

Makes authorization decisions.

Replaces older AccessDecisionManager.

3. AccessDecisionManager (Legacy Model)

Used in older versions.

Works with:

AccessDecisionVoter

Config attributes

4. GrantedAuthority

Represents a permission/role.

Example:

ROLE_ADMIN

ROLE_USER

SCOPE_read

Stored inside Authentication.

5. Method-Level Security

Used for fine-grained authorization:

@PreAuthorize

@PostAuthorize

@Secured

@RolesAllowed

Example:

@PreAuthorize("hasRole('ADMIN')")

6. ExceptionTranslationFilter

Catches security exceptions.

Delegates to:

AuthenticationEntryPoint → 401

AccessDeniedHandler → 403

7. AuthenticationEntryPoint

Triggered when user is not authenticated.

Returns 401 or redirects to login page.

8. AccessDeniedHandler

Triggered when user lacks permission.

Returns 403 Forbidden.

6. Explain HTTP Session?
An HTTP session is a server-side mechanism that maintains user state across multiple HTTP requests. It works by generating a unique session ID stored in a cookie, which allows the server to retrieve stored user data on subsequent requests.

7. Explain Cookie?
A cookie is a small piece of client-side data stored by the browser and automatically sent with each HTTP request to the same server. It is commonly used to maintain sessions, store preferences, and track user interactions. Cookies can be secured using attributes like HttpOnly, Secure, and SameSite.

8. Compare Session and Cookie?
A cookie is client-side storage used to maintain state across HTTP requests, while a session is server-side storage that keeps user-specific data. Typically, a cookie stores the session ID, and the session stores the actual user information. Sessions are more secure because the data remains on the server.

9. Find at least TWO websites who can be logged in using your Google Account, explain in detail on how Google SSO works with screenshots like below, find SSO-related Rest calls in Chrome developer tool:
When you click “Continue with Google”, you’re typically doing:
Authorization Request (browser → Google)
User signs in / consents (on Google pages)
Redirect back to your app with proof (code or token)
(Often) Token exchange (app backend ↔ Google)
App establishes its own session (cookie) or returns JWT to frontend

10. How do we use session and cookie to keep user information across the the application?
We use cookies to store a session identifier on the client side, and the server uses that identifier to retrieve user-specific data stored in a session. This allows the application to maintain user state across multiple HTTP requests while keeping sensitive data secure on the server.

11. What is the spring security filter?
The Spring Security filter is a chain of servlet filters that intercept HTTP requests before they reach the controller to perform authentication, authorization, session management, CSRF protection, and exception handling. It is managed internally by FilterChainProxy and configured through SecurityFilterChain.

12. Explain bearer token and how JWT works.
A bearer token is an access token sent in the Authorization header that grants access to protected resources. JWT is a commonly used bearer token format consisting of a header, payload, and signature. The server verifies the signature and claims to authenticate the user without storing session state, making JWT authentication stateless and scalable.

13. Explain how do we store sensitive user information such as password and credit card number in DB?
Passwords should be stored using strong one-way hashing algorithms like BCrypt with salt, so they cannot be reversed. Credit card numbers should not be stored directly; instead, we use tokenization via payment providers or strong encryption such as AES if storage is required. Plain text storage is never acceptable.

14. Compare UserDetailService, AuthenticationProvider, AuthenticationManager, AuthenticationFilter?(把这⼏个名字看熟悉也⾏)

15. What is the disadvantage of Session? how to overcome the disadvantage?
Session-based authentication has disadvantages including scalability issues, server memory usage, failover problems, and difficulty in distributed systems. These can be mitigated by using distributed session storage like Redis or by adopting stateless authentication mechanisms such as JWT, which remove the need for server-side session storage.

16. how to get value from application.properties in Spring security?
In Spring Security, we can retrieve values from application.properties using @Value for simple cases or @ConfigurationProperties for structured and scalable configuration. The recommended approach is @ConfigurationProperties because it provides type safety and better organization. Sensitive values should be externalized using environment variables.

17. What is the role of configure(HttpSecurity http) and configure(AuthenticationManagerBuilder auth)?
configure(HttpSecurity http) is used to define how HTTP requests are secured, including authorization rules, login/logout behavior, CSRF, and session management. configure(AuthenticationManagerBuilder auth) is used to define how authentication is performed, such as in-memory users, database authentication, or custom UserDetailsService. In short, HttpSecurity configures authorization, and AuthenticationManagerBuilder configures authentication.

18. Reading, 泛读⼀下即可，⾃⼰觉得是重点的，可以多看两眼。https://www.interviewbit.com/spring-security-i
nterview-questions/#is-security-a-cross-cutting-concern
1. 1-12
2. 17 - 30
19. Explain best practices to securely store secrets in applications
Best practice is to keep secrets out of source control, store them in a secret manager (or injected environment variables), enforce least privilege, encrypt at rest and in transit, rotate secrets regularly, prevent leaks by redacting logs, and secure CI/CD using dedicated secret stores and access controls. Frontends should never contain real secrets