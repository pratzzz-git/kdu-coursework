Homework 6

Hospital Staffing Evolution (Spring Data JPA)
Context
The hospital staffing system is scaling up! To manage the complexity, we are migrating from raw JDBC to Spring Data JPA. This will allow us to model the logical connections between Doctors, Shifts, and Departments more easily.
You are tasked with building a robust, layered application that handles data relationships and complex lookups.
Architectural Requirements
To maintain a professional codebase, you must follow this package structure:
Entity
Repo
Service
Controller
Exception
Model: DTOs for API requests/responses.

Exercise 1: Entity Mapping & Relationships
Goal: Map database tables to Java objects and define how they relate to one another.
Task 1 (Lombok & Entities): Define the four entities (ShiftType, Shift, User, ShiftUser) using JPA annotations. Use Lombok (@Data, @NoArgsConstructor) to keep the code clean.
Task 2 (Relationship Mapping): Properly define the relationships. For example:
One ShiftType can have many Shifts (One-to-Many).
A ShiftUser links a User and a Shift (Many-to-One mappings).
Task 3 (CRUD Endpoints): Create standard REST endpoints to save data for each of the 4 entities using the built-in .save() method of your repositories.
Success Criteria: Tables are automatically generated in your DB (check your hbm2ddl.auto setting) with correct Foreign Key constraints.


Exercise 2: Smart Lookups (Pagination & Custom Queries)
Goal: Use the power of JPA Method Names and JPQL for advanced data retrieval.
Task 1 (Controlled Pagination): Create an endpoint GET /users to fetch staff members.
Requirements: Support page (default 0) and size (default 50) as query parameters.
Constraints: Minimum size is 1; maximum size allowed is 50. Validate these in your service layer.
Task 2 (The "New Year" Search): Write a custom JPA query (using @Query or method naming) to find the Top 3 Shifts that started on "01-Jan-2023" and ended by "25-Jan-2023".
Requirement: The results must be sorted by shiftName in Ascending order.
Success Criteria: The API returns exactly 3 results for the date range, ordered alphabetically.

Final Deliverable: The "Drift" JPA Demo
Task: Record a video demonstration showing:
Architecture: Show your package structure and the relationship annotations (@ManyToOne, etc.) in your entities.
Naming Strategy: Explain which JPA naming strategy you chose in application.properties.
Advanced Queries: Demonstrate the "Top 3 Shifts" lookup and the Paginated User list in Postman.

