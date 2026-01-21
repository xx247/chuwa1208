1. List all of the annotations you learned from class and homework to annotaitons.md
@RestController
@RequestMapping
@PostMapping
@RequestBody
@PutMapping
@RequestBody
@PathVaribale
@Entity , @Table , @Id , @Column

2. Type out the code for the Comment feature of the class project.

3. In postman, call all of the APIs in PostController and CommentController.

4. What is JPA? and what is Hibernate?
JPA (Java Persistence API) is a specification (a standard) for how Java objects are stored in a database.
JPA defines WHAT methods and annotations exist, but does not implement them.
Hibernate is a concrete implementation of JPA.
Hibernate is a popular implementation of JPA that performs the actual database operations such as SQL generation, execution, and transaction management.

5. What is Hiraki? what is the benefits of connection pool?
HikariCP is a high-performance JDBC connection pool used to manage and reuse database connections efficiently in Java applications.
A connection pool improves performance and scalability by reusing database connections, reducing connection creation overhead, and preventing database overload.

6. What is the @OneToMany, @ManyToOne, @ManyToMany ? write some examples.
These three annotations describe relationships between database tables in JPA/Hibernate.
@OneToMany
Meaning: One record in table A can be related to many records in table B
Example idea: One Post, many Comments
@ManyToOne (MOST COMMON)
Meaning: Many records belong to one record
Example: Many Comments belong to one Post
@ManyToMany:
Represents a bidirectional relationship where multiple entities are associated with multiple entities using a join table.

7. What is the cascade = CascadeType.ALL, orphanRemoval = true ? and what are the other CascadeType
and their features? In which situation we choose which one?
cascade = CascadeType.ALL:
Any operation applied to the parent entity will automatically be applied to its child entities.
CascadeType.ALL includes all of these:PERSIST, MERGERE, MOVE, REFRESH, DETACH
orphanRemoval = true:
If a child entity is removed from the parent’s collection, it will be deleted from the database.
CascadeType.PERSIST
When you save the parent, save the child too.
CascadeType.MERGE
When you update the parent, update the child too.
CascadeType.REMOVE
When you delete the parent, delete the child too.
CascadeType.REFRESH
Reload child from DB when parent is refreshed.
CascadeType.DETACH
Detach child when parent is detached from persistence context.

8. What is the fetch = FetchType.LAZY, fetch = FetchType.EAGER ? what is the difference? In which
situation you choose which one?
fetch defines WHEN related entities are loaded from the database.
FetchType.EAGER:
Load the related entity immediately together with the parent entity.
FetchType.LAZY:
Load the related entity only when it is actually accessed.
Use LAZY when:
Relationship is large
You don’t always need related data
You care about performance
API returns DTOs
Real production systems
Use EAGER when:
Relationship is small
Data is always needed
One-to-one lookup
Read-only reference data

9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list
some examples?
JPA naming convention allows developers to define repository query methods by method names, and Spring Data JPA automatically generates the corresponding queries.
No, Spring Data JPA automatically implements methods that follow the naming convention.
List<Comment> findByPostId(Long postId);
Page<Post> findByTitle(String title, Pageable pageable);

10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the
naming convention to use the method provided by JPA.

11. (Optional) Check out a new branch(https://github.com/TAIsRich/springboot-redbook/tree/hw02_01_jdbcT
emplate) from branch 02_post_RUD, replace the dao layer using JdbcTemplate.

12. type the code, you need to checkout new branch from branch 02_post_RUD, name the new branch with h
ttps://github.com/TAIsRich/springboot-redbook/tree/hw05_01_slides_JPQL.

13. What is JPQL?
JPQL (Java Persistence Query Language) is a database-independent query language used to query JPA entities, not database tables.
JPQL queries Java objects and their fields, and JPA (Hibernate) translates them into SQL for your database.
14. What is @NamedQuery and @NamedQueries?
@NamedQuery allows you to define a static JPQL query with a name, so it can be reused anywhere in the application.
It is defined once, usually on an @Entity, and referenced by name.
@NamedQueries is just a container annotation that lets you define multiple @NamedQuery annotations on the same entity.

15. What is @Query? In which Interface we write the sql or JPQL?
@Query is a Spring Data JPA annotation that lets you define a query explicitly (JPQL or SQL) instead of relying on method-name conventions.

16. What is HQL and Criteria Queries?
HQL is Hibernate’s object-oriented query language that operates on entities and is translated into SQL by Hibernate.
Criteria Queries are a type-safe, programmatic way to build dynamic database queries using Java code instead of query strings.

17. What is EnityManager?
EntityManager is the core JPA interface responsible for managing entity lifecycle, persistence context, and database operations.

18. What is SessionFactory and Session?
SessionFactory is a thread-safe Hibernate object responsible for creating Session instances and managing Hibernate configuration.
Session is a single-threaded Hibernate object that represents a unit of work and manages entity persistence and database interaction.

19. What is Transaction? how to manage your transaction?
A transaction is a group of database operations that are treated as one single unit of work.
Either all operations succeed, or all are rolled back
Transactions are managed declaratively using the @Transactional annotation at the service layer.

20. What is hibernate Caching? Explain Hibernate caching mechanism in detail.
Hibernate caching is a performance optimization mechanism that reduces unnecessary database access by storing frequently used data in memory.
Hibernate provides a mandatory first-level cache scoped to a session and an optional second-level cache shared across sessions.

21. What is the difference between first-level cache and second-level cache?
The first-level cache is mandatory and scoped to a Hibernate session, while the second-level cache is optional, shared across sessions, and improves performance for repeated data access.

22. How do you understand @Transactional? (https://github.com/TAIsRich/tutorial-transaction)
@Transactional is a Spring annotation that defines a transactional boundary, ensuring that a group of database operations execute atomically with automatic commit or rollback.