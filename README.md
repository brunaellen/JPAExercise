# Project developed during the (Persistence with JPA: Introduction to Hibernate Course)

## I developed this project to learn and put in practice the JPA concept using hibernate as implementation.

* Learn how to add JPA to a Java application with Maven.
* How to map JPA entities and their relationships.
* Understand how the lifecycle of a JPA entity works.
* Perform queries via JPQL.

## About The Project
The main focus of this application is to practice the persistence of data to a database using JPA specification and hibernate implementation.

The application register products and their categories within a database(Mysql) and also retrieve information from the database of the registered products.

### Specification:
#### model package:
I created two classes, the entity classes (Product and Category);
 - A product has an id, name, descripton, price and date of registration.
 - A category has an id and name.

#### dao package:
I created two classes to make queries in the database(ProductDao and CategoryDao).
- Through the ProductDao class is possible to register a product, search all registered products, search a registered product price by its name, search a registered product by id, name or its category name.

- Through the CategoryDao class is possible to register, update or remove a category.

#### util package:
The JPAUtil class has a method that creates a new EntityManager passing the database name. This method returns a new EntityManager instance each time it is invoked.
