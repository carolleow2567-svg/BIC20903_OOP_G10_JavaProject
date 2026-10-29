A Java console application for managing the sharing and reuse of electronic equipment within a community. The project is developed for the **BIC20903 / BIC20904 Object-Oriented Programming Group Project** under **Sustainable Development Goal (SDG) 11: Sustainable Cities and Communities**, with a focus on Target 11.6.

> **Project status:** This README describes the agreed project design and intended functionality. Update the implementation status, screenshots, names, and any feature descriptions so they accurately reflect the final submitted code.

## Table of Contents

- [Project Overview](#project-overview)
- [Problem Statement](#problem-statement)
- [Objectives](#objectives)
- [Project Scope](#project-scope)
- [Main Features](#main-features)
- [Technology Stack](#technology-stack)
- [System Design](#system-design)
- [Class Responsibilities](#class-responsibilities)
- [Object-Oriented Programming Concepts](#object-oriented-programming-concepts)
- [Environmental Calculations](#environmental-calculations)
- [Data Management and Validation](#data-management-and-validation)
- [Getting Started](#getting-started)
- [How to Use the System](#how-to-use-the-system)
- [Example Workflow](#example-workflow)
- [Testing Checklist](#testing-checklist)
- [Team Responsibilities](#team-responsibilities)
- [Project Structure](#project-structure)
- [Limitations and Assumptions](#limitations-and-assumptions)
- [Future Improvements](#future-improvements)
- [Academic Context](#academic-context)
- [Contributors](#contributors)

## Project Overview

The **Community Electronic Equipment Sharing System** is designed to help a community keep track of electronic equipment that can be shared and reused instead of being discarded prematurely. The system maintains equipment details, member information, and sharing transactions.

The application is planned as a **Java console-based program**. Users interact with a menu to manage records, search and display information, record equipment borrowing and returns, and view summary calculations. Data is intended to be saved to local files and loaded when the program starts.

The project demonstrates core Java Object-Oriented Programming (OOP) concepts, including encapsulation, constructors, inheritance, polymorphism, collections, input validation, exception handling, and basic file handling.

## Problem Statement

Electronic devices may be discarded while they are still usable or repairable. In a community, people may also need equipment temporarily and may purchase a new item when a shared item could meet their needs. Without an organised record system, it can be difficult to identify available equipment, track who is using it, record returns, and report how many items have been reused.

This project proposes a simple record-management system to organise community electronic equipment sharing. It aims to make equipment status and sharing history easier to track and to provide a basic indicator of reuse. The application does not directly measure environmental pollution or prove that every shared item prevented disposal; its environmental results depend on the stated assumptions and the quality of the records entered.

## Objectives

The project aims to:

1. Develop a Java application to manage electronic equipment, community members, and sharing records.
2. Implement OOP concepts including encapsulation, constructors, inheritance, and polymorphism.
3. Provide record operations to add, remove, search, update, and display relevant information.
4. Record equipment borrowing and returns while preventing invalid transactions.
5. Validate user input and handle errors using Java exception handling.
6. Save records to local files and load saved data when the application starts.
7. Generate summary reports and at least two calculations, including an environmental reuse indicator related to SDG 11.6.

## Project Scope

### Included in the planned system

- Register, view, search, update, and remove equipment records.
- Support equipment types such as laptops and tablets.
- Register and manage community member records.
- Record equipment sharing transactions and returns.
- Track the current availability status of equipment.
- Display transaction and equipment summaries.
- Calculate the number of qualifying reused items and an equipment reuse rate.
- Validate input and handle common errors.
- Save and load application records using local files.

### Not required for this project

- A web or mobile interface.
- A database server.
- Real-time sensors or live environmental measurements.
- Online payments, public user accounts, or cloud synchronisation.
- Automatic proof that an item would otherwise have been discarded.

These exclusions keep the project manageable as a console-based OOP assignment.

## Main Features

| Feature | Description |
|---|---|
| Equipment management | Add, view, search, update, and remove equipment records. |
| Equipment categories | Represent different equipment types using subclasses such as `Laptop` and `Tablet`. |
| Member management | Maintain member IDs and basic contact details. |
| Sharing transactions | Record which member borrows which item and whether it has been returned. |
| Availability tracking | Identify whether equipment is available or currently borrowed. |
| Search and display | Find records by identifiers and display useful information. |
| Summary reports | Show counts of equipment, members, and sharing records. |
| Environmental indicator | Report qualifying reused items and calculate a reuse rate from eligible records. |
| Input validation | Reject invalid values and handle missing or malformed input. |
| File persistence | Save records locally and load them in a later run. |

> Only describe a feature as complete in a final submission if it is implemented and tested in the submitted source code.

## Technology Stack

- **Programming language:** Java
- **Interface:** Console / command-line menu
- **Data structures:** `ArrayList` or another suitable Java collection
- **Persistence:** Basic local file saving and loading
- **Development tools:** Any Java-compatible IDE or editor, such as IntelliJ IDEA, Eclipse, NetBeans, or Visual Studio Code with a Java development setup
- **Version control (recommended):** Git and GitHub

No external library is required for the proposed basic version unless the team chooses to add one.

## System Design

### Class relationship overview

The proposed design contains six main classes:

```text
                    Equipment
                   /         \
               Laptop       Tablet

                 Member

              SharingRecord

       EquipmentSharingSystem
                (main)
```

- `Equipment` is the parent class for shared equipment attributes and behaviour.
- `Laptop` and `Tablet` inherit from `Equipment` and provide type-specific details.
- `Member` stores information about a community member.
- `SharingRecord` represents a borrowing/return transaction.
- `EquipmentSharingSystem` coordinates the menu, collections, record operations, calculations, validation, and file handling.

The main class maintains collections of equipment, members, and sharing records. A sharing record should refer to existing member and equipment IDs. The equipment object's current status should represent its present availability, while sharing records preserve transaction history.

### Suggested data relationships

- One member may have multiple sharing records over time.
- One equipment item may appear in multiple sharing records over time.
- Each sharing record refers to one member and one equipment item.
- A piece of equipment should not be borrowed when it is already unavailable.

These relationships are implemented through IDs and application-level validation in the proposed console system.

## Class Responsibilities

### 1. `Equipment` — Parent class

**Purpose:** Stores information and common behaviour shared by all electronic equipment.

Suggested attributes:

- `equipmentID`
- `name`
- `brand`
- `condition`
- `status`
- `estimatedWeightKg` (optional, if used by the environmental reporting design)

Suggested responsibilities:

- Provide constructors, accessors, and mutators.
- Keep fields private to support encapsulation.
- Provide common display behaviour.
- Define an overridable or abstract method such as `displayDetails()` for polymorphism.
- Store the current availability status.

### 2. `Laptop` — Equipment subclass

**Purpose:** Represents a laptop and its specific specifications.

Suggested additional attributes:

- `ramGB`
- `storageGB`

Suggested responsibilities:

- Extend `Equipment`.
- Initialise inherited attributes through `super(...)`.
- Validate type-specific values.
- Override `displayDetails()` to include laptop specifications.

### 3. `Tablet` — Equipment subclass

**Purpose:** Represents a tablet and its specific specifications.

Suggested additional attributes:

- `screenSizeInches`
- `storageGB`

Suggested responsibilities:

- Extend `Equipment`.
- Initialise inherited attributes through `super(...)`.
- Validate type-specific values.
- Override `displayDetails()` to include tablet specifications.

### 4. `Member`

**Purpose:** Stores community member details.

Suggested attributes:

- `memberID`
- `name`
- `contactNumber`
- `email`

Suggested responsibilities:

- Provide constructors, accessors, mutators, and a display method.
- Validate required fields and basic contact formats.
- Work with the main system to prevent duplicate member IDs.
- Supply member details for sharing records and reports.

### 5. `SharingRecord`

**Purpose:** Tracks equipment borrowing and returns.

Suggested attributes:

- `recordID`
- `memberID`
- `equipmentID`
- `borrowDate`
- `returnDate`
- `status` (for example, `BORROWED` or `RETURNED`)

Suggested responsibilities:

- Store transaction information.
- Update the transaction status when an item is returned.
- Validate that the referenced member and equipment exist before a transaction is created.
- Prevent invalid transactions, such as borrowing an unavailable item.
- Provide transaction details for searches and reports.

### 6. `EquipmentSharingSystem` — Main class

**Purpose:** Runs the application and coordinates all classes.

Suggested responsibilities:

- Provide the `main()` entry point and menu loop.
- Maintain collections such as `ArrayList<Equipment>`, `ArrayList<Member>`, and `ArrayList<SharingRecord>`.
- Implement add, delete, search, update, and display operations.
- Coordinate borrowing and return operations.
- Check unique IDs and references between records.
- Handle input validation and exceptions.
- Implement the environmental calculations and summary reports.
- Save and load records using local files.

The main class should coordinate the application rather than duplicate the data and behaviour already assigned to the other classes.

## Object-Oriented Programming Concepts

The application is designed to demonstrate the following concepts:

| Concept | Planned use |
|---|---|
| Classes and objects | Model equipment, members, and sharing transactions. |
| Encapsulation | Keep attributes private and expose controlled access through methods. |
| Constructors | Initialise new objects with valid starting values. |
| Accessors and mutators | Retrieve or update object data in a controlled way. |
| Inheritance | `Laptop` and `Tablet` extend `Equipment`. |
| Polymorphism | Override `displayDetails()` and call it through an `Equipment` reference. |
| Collections | Use an `ArrayList` to store and manage records. |
| Exception handling | Handle invalid input and file errors without abruptly terminating the program. |
| File handling | Save and reload records between application runs. |

### Example of inheritance and polymorphism

The following is an illustrative Java snippet, not a complete application:

```java
Equipment item = new Laptop(
    "EQ001",
    "Student Laptop",
    "ExampleBrand",
    "Good",
    "AVAILABLE",
    16,
    512
);

item.displayDetails();
```

For this example to compile, the `Equipment` and `Laptop` constructors and the `displayDetails()` method must be defined with matching parameter types in the actual implementation. The design should use the same method signature in the parent and subclasses.

## Environmental Calculations

The assignment's suggested indicator for Topic 27 is **items reused instead of discarded**. The system can also provide a reuse-rate percentage as a second calculation.

### Calculation 1: Qualifying items reused

**Formula**

```text
Qualifying items reused = Number of unique equipment items
                          that meet the project's reuse criteria
```

Count unique equipment IDs, not the number of borrowing transactions. If one laptop is shared five times, it should not automatically count as five separate items reused.

The report should state the assumption used to classify an item as reused—for example, that a qualifying item was a previously owned or surplus item made available for another user's use instead of being discarded. The system's records cannot independently verify what would have happened to an item without sharing.

**Example**

If 12 unique equipment items meet the reuse criteria, the system reports **12 qualifying items reused**.

### Calculation 2: Equipment reuse rate

**Formula**

```text
Reuse rate (%) = (Qualifying reused items / Eligible items) × 100
```

Where:

- **Qualifying reused items** is the number of unique items that meet the reuse criteria.
- **Eligible items** is the number of items eligible for reuse in the same reporting period, using a clearly defined and consistent rule.

**Example**

If 12 of 30 eligible items qualify as reused:

```text
Reuse rate = (12 / 30) × 100
           = 40%
```

The reported reuse rate is **40%**.

### Calculation rules and limitations

- Use the same reporting period and item eligibility rules for the numerator and denominator.
- Avoid counting the same item multiple times in the qualifying-item count.
- Check that the denominator is greater than zero before calculating a percentage.
- If no items are eligible, display a message such as `Reuse rate cannot be calculated because there are no eligible items`.
- Identify the result as record-based and assumption-dependent; do not present it as a direct measurement of pollution reduction or carbon emissions.
- Explain how the indicator supports the aim of reducing adverse urban environmental impact under SDG 11.6.

## Data Management and Validation

### Record integrity

The application should enforce the following rules:

1. Equipment IDs, member IDs, and sharing record IDs must be unique.
2. A sharing record must refer to an existing member and equipment item.
3. Only available equipment may be borrowed.
4. A return should only be recorded for an active borrowing transaction.
5. Equipment currently involved in an active transaction should not be deleted unless the application first handles that transaction safely.
6. Required fields must not be blank.
7. Numeric fields such as RAM, storage, and screen size must contain valid positive values where appropriate.
8. Date and status values must be valid and consistent.
9. Invalid menu choices should produce a helpful message and allow the user to try again.

### Exception handling

Use `try-catch` where appropriate to handle issues such as:

- Non-numeric input where a number is expected.
- Invalid dates or numeric values.
- Missing or unreadable data files.
- File read/write errors.
- Unexpected or malformed saved records.

Exception handling should provide understandable feedback and preserve valid application data where possible.

### File saving and loading

The proposed application uses local files for basic persistence. The team should choose and document a consistent format, such as one text or CSV-style file per record type.

Recommended data files might include:

```text
data/
├── equipment.txt
├── members.txt
└── sharing_records.txt
```

This is a suggested structure; the actual file names and format should match the implemented code.

The implementation should:

- Load saved records at startup.
- Save changes after suitable operations or before exiting.
- Handle a missing file as an initial empty data store where appropriate.
- Validate loaded records before adding them to collections.
- Avoid overwriting valid data when saving fails.
- Use a consistent delimiter or escaping strategy so text fields do not corrupt the file format.

## Getting Started

### Prerequisites

1. Install a Java Development Kit (JDK) compatible with the Java version used by your team.
2. Confirm Java is available in your terminal.
3. Obtain the project source code from the team repository or project folder.

Check your Java installation:

```bash
java -version
javac -version
```

### Compile and run

The exact commands depend on your source layout and package declarations. For a simple project with all six `.java` files in the same directory and no package declarations, use:

```bash
javac Equipment.java Laptop.java Tablet.java Member.java SharingRecord.java EquipmentSharingSystem.java
java EquipmentSharingSystem
```

If the project uses packages or a `src` directory, compile and run it according to that structure or through your IDE.

### Clone the repository (if hosted on GitHub)

Replace the placeholder URL with the team's actual repository URL:

```bash
git clone <YOUR_REPOSITORY_URL>
cd <YOUR_PROJECT_FOLDER>
```

Do not commit private contact information or real personal records. Use fictional sample data for demonstrations.

## How to Use the System

The exact menu wording depends on the final implementation. A recommended menu flow is:

1. Start the application.
2. Load saved records.
3. Select an operation from the main menu.
4. Manage equipment records.
5. Manage member records.
6. Create a sharing record when a member borrows available equipment.
7. Record the return of borrowed equipment.
8. Search or display records to check their current status.
9. View summary reports and environmental calculations.
10. Exit the application after ensuring changes are saved.

A possible menu layout is:

```text
========================================
 COMMUNITY ELECTRONIC EQUIPMENT SHARING
========================================
1. Manage Equipment
2. Manage Members
3. Borrow Equipment
4. Return Equipment
5. View Sharing Records
6. Search Records
7. Environmental Summary
8. Save Data
0. Save and Exit
Enter your choice:
```

## Example Workflow

A typical demonstration could follow these steps:

1. Add a laptop with ID `EQ001` and status `AVAILABLE`.
2. Add a tablet with ID `EQ002` and status `AVAILABLE`.
3. Register a member with ID `M001`.
4. Create a sharing record linking `M001` to `EQ001`.
5. Confirm that `EQ001` changes to `BORROWED` or the equivalent unavailable status.
6. Record the return and confirm that the equipment becomes available again.
7. Search for `EQ001` and display its current status.
8. View the environmental summary and verify that the calculations use unique qualifying items and the defined eligible-item count.
9. Exit and restart the program to confirm that records are loaded from files.

Use only sample records that are consistent with the final implementation.

## Testing Checklist

Before submission, test the following behaviours.

### Equipment and member records

- [ ] Add valid equipment and member records.
- [ ] Reject duplicate IDs.
- [ ] Search for an existing ID.
- [ ] Show a helpful message for an unknown ID.
- [ ] Update a record and confirm the new values are displayed.
- [ ] Delete a record safely.
- [ ] Reject blank required fields and invalid numeric values.

### Sharing transactions

- [ ] Borrow available equipment successfully.
- [ ] Prevent borrowing equipment that is already borrowed.
- [ ] Reject a transaction that references a non-existent member or item.
- [ ] Return equipment that has an active borrowing record.
- [ ] Prevent returning an item that is not currently borrowed.
- [ ] Confirm equipment status and transaction status remain consistent.

### OOP requirements

- [ ] Demonstrate constructors and private fields.
- [ ] Demonstrate inheritance from `Equipment` to `Laptop` and `Tablet`.
- [ ] Demonstrate method overriding and polymorphic method calls.
- [ ] Demonstrate collections of objects.

### Calculations and reports

- [ ] Verify the qualifying reused-item count.
- [ ] Verify the reuse-rate formula with a known example.
- [ ] Confirm repeated transactions for one item do not inflate a unique-item count.
- [ ] Handle a zero denominator without crashing.
- [ ] Explain the environmental assumptions in the report.

### File handling and robustness

- [ ] Save valid records and restart the application.
- [ ] Confirm records load correctly.
- [ ] Test the behaviour when a data file does not exist.
- [ ] Test malformed or unreadable data.
- [ ] Test invalid menu input.
- [ ] Confirm a file error produces a clear message.

## Team Responsibilities

The project is planned for six members, with one main class assigned to each person.

| Member | Main class | Main responsibilities |
|---|---|---|
| Member 1 | `Equipment` | Shared equipment fields, constructors, accessors/mutators, common behaviour, and parent-class design. |
| Member 2 | `Laptop` | Laptop-specific fields, constructor, validation, inheritance, and overridden display behaviour. |
| Member 3 | `Tablet` | Tablet-specific fields, constructor, validation, inheritance, and overridden display behaviour. |
| Member 4 | `Member` | Member details, validation, and member-record behaviour. |
| Member 5 | `SharingRecord` | Borrowing/return transaction data, transaction status, and transaction validation. |
| Member 6 | `EquipmentSharingSystem` | Main menu, collections, CRUD coordination, integration, file persistence, reports, and calculations. |

### Shared responsibilities

Each member should test their own class and help with integration testing. The team should agree on class names, attributes, constructors, method signatures, ID rules, and status values before parallel coding begins.

The main class has the largest integration responsibility. Members should support it by providing compilable classes, sample data, test cases, and timely fixes. The UML diagram, report, and presentation should reflect the actual final code rather than only the initial plan.

## Project Structure

A suggested simple structure is:

```text
CommunityElectronicEquipmentSharingSystem/
├── src/
│   ├── Equipment.java
│   ├── Laptop.java
│   ├── Tablet.java
│   ├── Member.java
│   ├── SharingRecord.java
│   └── EquipmentSharingSystem.java
├── data/
│   ├── equipment.txt
│   ├── members.txt
│   └── sharing_records.txt
├── docs/
│   ├── UML-Class-Diagram.png
│   └── screenshots/
├── README.md
└── .gitignore
```


## Limitations and Assumptions

- The application is intended for a small community and educational demonstration.
- It is a console-based system, not a production service.
- Local text files do not provide the reliability or concurrency protections of a database.
- The application relies on users to enter accurate equipment and transaction information.
- The environmental indicator is based on recorded items and stated assumptions; it is not a direct environmental impact measurement.
- The system does not independently determine whether an item would otherwise have been discarded.
- The initial design covers laptops and tablets. More equipment subclasses can be added if the team has time and the assignment scope permits.

## Future Improvements

Possible improvements beyond the initial assignment include:

- Add subclasses for monitors, keyboards, and other devices.
- Add equipment condition and repair-history tracking.
- Add sorting and filtering for equipment and transaction records.
- Generate exportable summary reports.
- Replace text files with a database when database use is appropriate.
- Add a graphical user interface or web interface.
- Add user authentication and role-based permissions.
- Add audit logs for important record changes.
- Improve validation and automated unit testing.
- Track the lifecycle of equipment from donation to reuse or retirement.

These are potential extensions, not part of the required initial scope.

## Academic Context

**Course:** BIC20903 / BIC20904 — Object-Oriented Programming  
**Project title:** Community Electronic Equipment Sharing System  
**Theme:** SDG 11 — Sustainable Cities and Communities  
**Target:** SDG 11.6 — Reduce the adverse per capita environmental impact of cities  
**Language:** Java  
**Application type:** Console-based record-management application

The project is designed to meet the assignment's stated OOP and record-management requirements. The final report should include an introduction, problem background, objectives, methodology, analysis and design (including a UML class diagram and calculation formulas), implementation evidence, results, and conclusions. Confirm all final requirements against the lecturer's project brief.

## Contributors

Add the six group members' names and student identifiers according to your lecturer's submission rules.

1. **Member 1:** `LEE YI SUEN (AI250266)` — `Equipment.java`
2. **Member 2:** `NGE (NG) SHI KEE (AI250232)` — `Laptop.java`
3. **Member 3:** `THARANE A/P MAILVAGANAM (CI250119)` — `Tablet.java`
4. **Member 4:** `LEE CHAI JIN (CI250120)` — `Member.java`
5. **Member 5:** `LEOW YI XING (AI250240)` — `SharingRecord.java`
6. **Member 6:** `TAN TIEN TIEN (AI250220)` — `EquipmentSharingSystem.java`