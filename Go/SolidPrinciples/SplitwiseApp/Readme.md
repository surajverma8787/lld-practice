# Splitwise Low-Level Design (LLD) — Go Implementation

---

# Quick Overview

## Tech Stack
- Language: Go
- Architecture: Service-Oriented Architecture
- Design Patterns:
    - Strategy Pattern
    - Factory Pattern
    - Dependency Injection

---

# Core Services

| Service | Responsibility |
|---|---|
| UserService | User management |
| GroupService | Group management and orchestration |
| ExpenseService | Expense creation and orchestration |
| SplitService | Split generation |
| BalanceService | Balance tracking |

---

# Core Models

| Model | Responsibility |
|---|---|
| User | Represents user |
| Group | Represents group |
| Expense | Represents expense |
| Split | Represents individual split |
| BalanceSheet | Tracks balances |

---

# Supported Split Types

- Equal Split
- Unequal Split
- Percentage Split

---

# Design Patterns Used

| Pattern | Usage |
|---|---|
| Strategy Pattern | Split validation |
| Factory Pattern | Strategy creation |
| Service Layer Pattern | Business logic separation |
| Dependency Injection | Loose coupling |

---

# Project Structure

```text
splitwise/
│
├── models/
│   ├── user.go
│   ├── expense.go
│   ├── split.go
│   ├── balance.go
│   ├── group.go
│   └── split_type.go
│
├── services/
│   ├── user_service.go
│   ├── group_service.go
│   ├── expense_service.go
│   ├── balance_service.go
│   └── split_service.go
│
├── strategy/
│   ├── split_strategy.go
│   ├── split_strategy_factory.go
│   ├── equal_split_strategy.go
│   ├── unequal_split_strategy.go
│   └── percentage_split_strategy.go
│
└── main.go
```

---

# 1. Problem Statement

Design and implement a simplified Splitwise application in Go that supports:

* User management
* Group management
* Expense creation
* Equal split
* Unequal split
* Percentage split
* Balance tracking between users

The project focuses on:

* Clean Architecture
* SOLID Principles
* Design Patterns
* Scalability
* Extensibility

---

# 2. Functional Requirements

## User Features

* Create user
* Fetch user
* View all users

---

## Group Features

* Create group
* Add users to group
* Create expenses inside group
* Track all expenses inside group

---

## Expense Features

A user should be able to:

* Create an expense
* Select split type
* Add participants
* Add remarks

Supported Split Types:

* Equal
* Unequal
* Percentage

---

## Balance Features

* Show balance for a user
* Show all balances
* Track who owes whom

---

# 3. Non Functional Requirements

* Extensible system
* Clean separation of concerns
* Maintainable codebase
* Reusable services
* Low coupling
* High cohesion
* Easy to add new split types

---

# 4. High Level Design

```text
                         +----------------------+
                         |     GroupService     |
                         +----------------------+
                                      |
                                      v
                         +----------------------+
                         |    ExpenseService    |
                         +----------------------+
                             |              |
                             v              v
                  +----------------+   +----------------+
                  | SplitService   |   | BalanceService |
                  +----------------+   +----------------+
                             |
                             v
                +--------------------------+
                | SplitStrategyFactory     |
                +--------------------------+
                             |
                             v
                +--------------------------+
                | Concrete Strategies      |
                | Equal / Unequal / %      |
                +--------------------------+
```

---

# 5. Core Entities

## User

Represents a user in the system.

```go
type User struct {
    userID   string
    username string
}
```

Responsibilities:

* Participates in expenses
* Belongs to groups

---

## Split

Represents how much a user owes.

```go
type Split struct {
    User       *User
    Amount     float64
    Percentage float64
}
```

---

## Expense

Represents an expense.

```go
type Expense struct {
    ExpenseID string
    PaidBy    *User
    Amount    float64
    Splits    []*Split
    Remarks   string
    SplitType SplitType
}
```

Responsibilities:

* Stores expense details
* Stores split information

---

## Group

Represents a collection of users.

```go
type Group struct {
    GroupID string
    Name    string

    Users    []*User
    Expenses []*Expense
}
```

Responsibilities:

* Stores users
* Stores group expenses

---

## BalanceSheet

Tracks balances between users.

```go
type BalanceSheet struct {
    Balances map[string]map[string]float64
}
```

Meaning:

```text
Balances[debtor][creditor] = amount
```

Example:

```text
Balances["U2"]["U1"] = 300
```

Meaning:

```text
User2 owes User1 ₹300
```

---

# 6. Class Diagram

```text
+-------------------+
|       User        |
+-------------------+
| userID            |
| username          |
+-------------------+

          ▲
          |
          |

+-------------------+
|       Split       |
+-------------------+
| User              |
| Amount            |
| Percentage        |
+-------------------+

          ▲
          |
          |

+-------------------+
|      Expense      |
+-------------------+
| ExpenseID         |
| PaidBy            |
| Amount            |
| Splits            |
| Remarks           |
| SplitType         |
+-------------------+

          ▲
          |
          |

+-------------------+
|       Group       |
+-------------------+
| GroupID           |
| Name              |
| Users             |
| Expenses          |
+-------------------+

+-------------------+
|   BalanceSheet    |
+-------------------+
| Balances          |
+-------------------+
```

---

# 7. Service Architecture

The application follows Service-Oriented Architecture.

Each service has a dedicated responsibility.

---

## UserService

Responsibilities:

* Create user
* Fetch user
* Fetch all users

Methods:

```go
CreateUser()
GetUser()
GetAllUsers()
```

---

## GroupService

Responsibilities:

* Create group
* Add users to group
* Create expenses inside group

Methods:

```go
CreateGroup()
AddUserToGroup()
GetGroup()
CreateExpenseInGroup()
```

This acts as the orchestration layer.

---

## ExpenseService

Responsibilities:

* Create expense
* Generate splits
* Validate splits
* Update balances

Methods:

```go
CreateExpense()
```

Flow:

```text
Create Expense
      ↓
Generate Splits
      ↓
Validate Splits
      ↓
Update Balance Sheet
      ↓
Store Expense
```

---

## SplitService

Responsibilities:

* Generate equal splits
* Generate unequal splits
* Generate percentage splits

Methods:

```go
CreateEqualSplits()
CreateUnequalSplits()
CreatePercentageSplits()
```

---

## BalanceService

Responsibilities:

* Maintain balances
* Show balances

Methods:

```go
UpdateBalance()
ShowBalanceForUser()
ShowAllBalances()
```

---

# 8. Design Patterns Used

## Strategy Pattern

Used for:

* Split validation

Interface:

```go
type SplitStrategy interface {
    ValidateSplitRequest(
        splits []*Split,
        totalAmount float64,
    ) error
}
```

Concrete Strategies:

* EqualSplitStrategy
* UnequalSplitStrategy
* PercentageSplitStrategy

Benefits:

* Extensible
* Cleaner code
* Removes if-else chains

---

## Factory Pattern

Used for:

* Returning correct strategy dynamically

Factory:

```go
GetSplitStrategy(splitType)
```

Benefits:

* Loose coupling
* Cleaner orchestration
* Easier extensibility

---

## Dependency Injection

Services are injected into other services.

Example:

```go
ExpenseService(
    splitService,
    balanceService,
)
```

Benefits:

* Better testability
* Low coupling
* Better maintainability

---

# 9. Expense Creation Flow

```text
User creates expense
        |
        v
GroupService.CreateExpenseInGroup()
        |
        v
ExpenseService.CreateExpense()
        |
        v
SplitService generates splits
        |
        v
SplitStrategyFactory returns strategy
        |
        v
Concrete strategy validates splits
        |
        v
BalanceService updates balances
        |
        v
Expense stored in group
```

---

# 10. Split Validation Flow

```text
Split Type
    |
    v
SplitStrategyFactory
    |
    v
Concrete Strategy
    |
    v
Validate Split Request
```

---

# 11. Balance Update Flow

```text
Expense Created
      |
      v
For each split:
      |
      v
debtor -> creditor -> amount
      |
      v
Update map[string]map[string]float64
```

Example:

```text
User2 owes User1 ₹300
```

Stored as:

```go
Balances["User2"]["User1"] = 300
```

---

# 12. APIs / Public Methods

## User APIs

```go
CreateUser(userID, username)
GetUser(userID)
GetAllUsers()
```

---

## Group APIs

```go
CreateGroup(groupID, name)
AddUserToGroup(groupID, user)
GetGroup(groupID)
CreateExpenseInGroup(...)
```

---

## Expense APIs

```go
CreateExpense(...)
```

---

## Split APIs

```go
CreateEqualSplits()
CreateUnequalSplits()
CreatePercentageSplits()
```

---

## Balance APIs

```go
UpdateBalance()
ShowBalanceForUser()
ShowAllBalances()
```

---

# 13. Example Scenario

Scenario:

```text
Expense Amount = ₹900
Paid By = User1

Participants:
- User1
- User2
- User3

Split Type = Equal
```

Generated Splits:

```text
User1 → 300
User2 → 300
User3 → 300
```

Balance Updates:

```text
User2 owes User1 ₹300
User3 owes User1 ₹300
```

Stored as:

```go
Balances["User2"]["User1"] = 300
Balances["User3"]["User1"] = 300
```

---

# 14. Complexity Analysis

| Operation       | Complexity |
| --------------- | ---------- |
| Create Expense  | O(n)       |
| Generate Splits | O(n)       |
| Update Balances | O(n)       |
| Show Balances   | O(n)       |

Where:

```text
n = number of users/splits
```

---

# 15. SOLID Principles Followed

## Single Responsibility Principle

Each service has a single responsibility.

Examples:

* SplitService → split generation
* BalanceService → balance tracking
* ExpenseService → expense orchestration

---

## Open Closed Principle

New split types can be added without modifying existing services.

Example:

```text
ExactRatioSplitStrategy
```

---

## Dependency Inversion Principle

Services depend on abstractions.

Example:

```go
SplitStrategy
```

instead of concrete implementations.

---

# 16. Interview Explanation

## Why Strategy Pattern?

Different split types have different validation rules.

Instead of:

```text
if splitType == EQUAL
else if splitType == UNEQUAL
else if splitType == PERCENTAGE
```

we encapsulate validation logic into separate strategies.

Benefits:

* Better extensibility
* Better readability
* Cleaner code

---

## Why Factory Pattern?

Factory returns correct strategy dynamically.

Benefits:

* Loose coupling
* Centralized object creation
* Easier extensibility

---

## Why Separate SplitService and Strategy?

SplitService:

* Generates splits

Strategy:

* Validates splits

This follows:

* SRP
* Better abstraction

---

## Why map[string]map[string]float64?

Efficient lookup.

Meaning:

```text
debtor -> creditor -> amount
```

Complexity:

```text
O(1)
```

---

## Why Service-Oriented Architecture?

Benefits:

* Better modularity
* Better scalability
* Better testing
* Clear separation of concerns

---

# 17. Future Improvements

Potential enhancements:

* UUID-based IDs
* Database persistence
* REST APIs
* Authentication
* Group-specific balances
* Expense settlement optimization
* Decimal-based money handling
* Transaction support
* Simplify debt graph
* Concurrency support

---

# 18. Conclusion

This project demonstrates:

* Clean Low-Level Design
* SOLID principles
* Design patterns
* Scalable architecture
* Service-oriented design
* Extensible split handling

Suitable for:

* Backend interviews
* LLD interviews
* Go architecture discussions
* Demonstrating system design skills