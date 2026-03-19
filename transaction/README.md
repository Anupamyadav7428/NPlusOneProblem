# 📌 N+1 Problem in Spring Boot – User & Expense Example

## 🚀 Overview

This project demonstrates the **N+1 Query Problem** in Spring Boot using **Spring Data JPA & Hibernate** with a real-world example of `User` and `Expense` entities.

It also includes **multiple solutions** to solve the problem and optimize performance.

### Goals:

- Understand why N+1 occurs
- Analyze its performance impact
- Implement and compare multiple solutions
- Learn best practices for APIs and entity fetching

---

## 🧠 What is N+1 Problem?

The N+1 problem occurs when:

- 1 query fetches parent entities (`User`)
- N queries fetch associated child entities (`Expense`)

This happens by default due to **lazy loading** in JPA.

---

### ❌ Example Code Causing N+1

```java
List<User> users = userRepository.findAll();

for (User user : users) {
    System.out.println(user.getExpenses().size()); // triggers separate queries for each user
}
```

### ❌ SQL Behavior

```
Query 1 → fetch all users
Query 2 → fetch expenses for user 1
Query 3 → fetch expenses for user 2
...
```

If there are 100 users → **101 queries executed** instead of 1 ✅

---

## ⚠️ Why N+1 Happens

Because of Lazy Loading:

```java
@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
private List<Expense> expenses;
```

Hibernate will **only fetch expenses when accessed**, causing multiple queries.

---

## 🧱 Entities

### User

```java
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Expense> expenses;
}
```

---

### Expense

```java
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Double amount;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private User user;
}
```

---

# ✅ Solutions to N+1 Problem

---

## 🔹 1. JOIN FETCH

### Concept

Fetch `User` and associated `Expense` entities **in a single query** using `JOIN`.

### Implementation

```java
@Query("SELECT DISTINCT u FROM User u JOIN FETCH u.expenses")
List<User> findAllWithExpenses();
```

### ✅ Pros
- Single optimized query
- Fully eliminates N+1

### ❌ Cons
- Duplicate user rows (use `DISTINCT`)
- Pagination issues may arise

---

## 🔹 2. @EntityGraph

### Concept

Use Spring Data JPA’s annotation to define which associations to fetch eagerly.

### Implementation

```java
@EntityGraph(attributePaths = "expenses")
@Query("SELECT u FROM User u")
List<User> findAllUsingEntityGraph();
```

### ✅ Pros
- Cleaner than JOIN FETCH
- Single query executed

### ❌ Cons
- Less flexible than JPQL
- Loads full entity

---

## 🔹 3. Batch Fetching

### Concept

Fetch child entities in **batches instead of one by one**, reducing query count.

### Implementation

```java
@OneToMany(mappedBy = "user")
@BatchSize(size = 10)
private List<Expense> expenses;
```

```properties
spring.jpa.properties.hibernate.default_batch_fetch_size=10
```

### Behavior

```
1 query → users
Few queries → expenses using IN clause
```

### ✅ Pros
- Reduces number of queries
- Works with lazy loading

### ❌ Cons
- Not fully eliminated
- Hibernate-specific

---

## 🔹 4. DTO Projection (Recommended 🚀)

### Concept

Fetch only the **fields you need** instead of full entity objects.

### DTO Class

```java
public class UserExpanseDTO {
    private String name;
    private String description;
    private double amount;

    public UserExpanseDTO(String name, String description, double amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }
}
```

### Repository Query

```java
@Query("SELECT new com.example.dto.UserExpenseDTO(u.name, e.description, e.amount) " +
       "FROM User u JOIN u.expenses e")
List<UserExpenseDTO> fetchUserExpenseDTO();
```

### ✅ Pros
- Best performance
- Only fetch required data
- Single query

### ❌ Cons
- Read-only (cannot update entities through DTO)
- Additional DTO class needed

---

## 🔹 5. SUBSELECT (Advanced)

### Concept

Fetch child entities for all users in **one extra query** using Hibernate’s `FetchMode.SUBSELECT`.

### Implementation

```java
@OneToMany(mappedBy = "user")
@Fetch(FetchMode.SUBSELECT)
private List<Expense> expenses;
```

### Behavior

```
Query 1 → fetch users
Query 2 → fetch all expenses WHERE user_id IN (all users)
```

### ✅ Pros
- Only 2 queries instead of N+1

### ❌ Cons
- Hibernate-specific
- Not suitable for very large datasets

---

# 📊 Comparison Table

| Solution | Queries | Use Case |
|----------|--------|----------|
| Lazy (Default) | ❌ N+1 | Avoid |
| JOIN FETCH | ✅ 1 | Entity fetching |
| EntityGraph | ✅ 1 | Cleaner JPQL |
| Batch Fetching | ⚠️ Few | Large datasets |
| DTO Projection | 🚀 1 | API responses |
| SUBSELECT | ✅ 2 | Medium data |

---

# 🔍 Detect N+1 Problem

Enable SQL logs in `application.properties`:

```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Look for:
- Repeated queries inside loops
- Multiple queries fetching the same entity

---

# 🚀 Best Practices

- Use **DTO Projection for APIs**
- Use **JOIN FETCH or EntityGraph** for entity fetching
- Avoid lazy loading inside loops
- Monitor queries with Hibernate SQL logs

---

# 👨‍💻 Author

**Anupam Yadav** – Spring Boot Backend Developer

---

# 🎯 Future Enhancements

- Add **REST APIs and Controllers**
- Add **@Transactional handling**
- Add **Logging & Exception Handling**
- Implement **Caching (Redis / Ehcache)**

---

# 💡 Notes

- N+1 is not a bug, but a **performance problem** due to query design
- Proper understanding of **JPA lazy vs eager loading** and fetch strategies is crucial