# Make the right move by splitting preparation

1. **Low-Level Design (LLD)**: To showcase your object-oriented modeling, abstraction, and design patterns—critical when they test "depth over scale."
2. **High-Level Design (HLD)**: To round out your system design coverage with real-world architectural themes (multi-region, observability, rate limiting, etc.).

Let’s structure each into focused 2-week sprints.

---

## 🧱 Low-Level Design (LLD) – 2 Week Plan

### 🎯 Goal:

Demonstrate mastery in **OOP principles**, **clean abstractions**, **design patterns**, and **testable modular code**. You’re not just writing classes—you’re **modeling behavior like a Principal**.

---

### 🗓️ **Week 1: OOP Foundations & Design Patterns**

#### Concepts:

* SOLID Principles in practice (not theory)
* Composition vs. Inheritance
* Interfaces, Abstract classes, Polymorphism
* Design patterns: Strategy, Observer, Factory, Decorator

#### Practice Exercises:

1. **Parking Lot Design**

    * Model parking floors, vehicle types, pricing strategy
    * Use: Strategy pattern for pricing, Observer for ticket updates
2. **Rate Limiter**

    * Token bucket or sliding window algorithm
    * Extend to support distributed (multi-node) limits
3. **Elevator System**

    * Emphasize abstraction: Request, Control, Movement
    * Use State pattern (Idle, Moving, DoorOpen)

#### Deliverables:

* Class diagrams (UML or PlantUML)
* Code snippets (Java preferred, use interfaces and tests)
* 1 Mock LLD session (with me)

---

### 🗓️ **Week 2: Clean Design & Testability**

#### Concepts:

* DRY, KISS, YAGNI in real code
* Dependency Injection, IoC
* Extensibility over modification
* Testing strategies: mocking, test doubles, contract tests

#### Practice Exercises:

1. **Notification System**

    * Abstract Email, SMS, Push
    * Use: Factory + Strategy patterns
2. **Chess or Tic-Tac-Toe**

    * Emphasize rules, players, board state, turns
    * Use: Command pattern (for undo/redo), Observer (game events)
3. **Cache Library**

    * Eviction policies: LRU, LFU
    * Thread-safe, extensible via interfaces

#### Deliverables:

* One fully tested module (e.g., rate limiter or cache)
* One test plan showing how you design for edge cases
* 1 Mock code walkthrough (how you’d explain your LLD in an interview)

---

## 🧠 High-Level System Design Topics – 2 Week Plan

### 🎯 Goal:

Address commonly **under-explored but critical architectural themes**. These make you stand out as a Principal—not just as someone who can “design Twitter,” but who understands **scalability, observability, and resilience at scale**.

---

### 🗓️ **Week 1: Scalable Platform Patterns**

#### Topics:

* **API Gateway vs. Service Mesh**
* **Authentication/Authorization patterns** (JWT, OAuth2, token rotation)
* **Rate Limiting and Throttling**
* **Feature Flags, Canary Deployments, Rollbacks**
* **Circuit Breakers, Retry Patterns (Resilience)**

#### Tasks:

* Design Discussion: How would you build an API Gateway from scratch?
* Real-World Framing: How did you secure and govern access to 20k+ apps? (Use your Wayfair/Meta work)

---

### 🗓️ **Week 2: Cross-Cutting System Concerns**

#### Topics:

* **Observability Strategy**: metrics, logs, traces, alerts
* **Multi-region Active-Active vs Active-Passive**
* **Data Migration and Backfill Strategies**
* **Schema Evolution and Backward Compatibility**
* **Cost-aware Architecture**: Trade-off performance vs spend

#### Tasks:

* Architecture Whiteboard: Design a multi-region notification service with failover and monitoring
* Personal Project Tie-in: How did you use multi-layer caching to save 22k compute hours?

---

## ✅ Combined Output in 4 Weeks

You'll have:

* 6+ modeled LLD problems (fully abstracted, testable)
* 2-3 mock interviews for LLD and HLD
* Architecture plans for authN/z, observability, API rate-limiting
* Talking points mapped to your real projects at Meta, Wayfair, and Coinflip

---