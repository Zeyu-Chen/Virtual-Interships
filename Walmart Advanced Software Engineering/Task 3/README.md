# Task 3: Relational Database Design

## Task Overview

### What you'll learn

- How to design a normalized relational database schema.
- How to represent different entities and their relationships in an Entity Relationship Diagram (ERD).

### What you'll do

- Meet Jonathan, a student ambassador on track to become a technical program manager at Walmart.
- Design a new database for Walmart’s pet department that satisfies a range of requirements.
- Unlock guidance about what skills, experience, and requirements Walmart looks for when hiring software engineering interns.

## Task Background

The Walmart pet department has decided to combine its numerous databases into a single source of truth. The centralized database will save much time and effort when it comes to querying the data and collecting metrics. They just got approval to begin the project, so now it’s up to your team to begin putting the database together. The first step is to figure out the database schema. You have been selected to design the database and are expected to have the first draft of an ERD completed shortly. The pet department has sent over a list of requirements the design must satisfy and is eager to get started. Good luck!

## Task Details

Your task is to draft an ERD for an appropriately normalized relational database that satisfies these requirements:

You may assume three classes already exist:

- **Datapoint**: this class represents both raw and processed data points. Any time data moves between methods you may use this class as an abstraction.
- **ModeIdentifier**: an enum used to identify a processor mode.
- **DatabaseIdentifier**: an enum used to identify a database connection.

### Requirements

- The database should store information related to the following products.

  - Pet food, which has a name, manufacturer, weight, flavor, and target health condition.
  - Pet toys, which have an associated material, name, manufacturer, and durability.
  - Pet apparel, which has a color, manufacturer, size, name, and specific care instructions.

- Each product should be associated with one or more animals.

- Each product should be associated with a manufacturer.

- The database should track customers and their transactions.

  - It should store customer names and email addresses.
  - Customers can make transactions to purchase one or more products.
  - Each transaction should store the date and the products involved.

- The database should track shipments to various Walmart locations.
  - Each location should be represented by a name and a zip code.
  - A shipment is recorded as an origin, a destination, and a collection of products, each with an associated quantity.

When you’re finished, convert your ERD to a PDF and submit it below. You'll see an example answer on the next step, but we encourage you to give it a go first!
