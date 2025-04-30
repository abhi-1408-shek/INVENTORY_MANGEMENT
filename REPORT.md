# Inventory Management System – Project Report

## Introduction
The Inventory Management System is a robust desktop solution designed to streamline the management of products, suppliers, categories, and inventory transactions for retail and warehouse environments. Built with JavaFX and MySQL, the system prioritizes usability, data integrity, and actionable business analytics.

## Objectives
- Simplify inventory, supplier, and category management
- Enable real-time tracking of stock and transactions
- Provide analytics and reporting for business decision-making
- Ensure data consistency and security

## System Architecture
- **Design Pattern:** Model-View-Controller (MVC)
- **Persistence:** Data Access Object (DAO) for all DB operations
- **Database:** MySQL, normalized schema with foreign key constraints
- **UI:** JavaFX (modern, responsive, with charts and dashboards)

## Database Schema
- **categories:** id (PK), name (unique)
- **suppliers:** id (PK), name, contact_info
- **products:** id (PK), name, category_id (FK), supplier_id (FK), price, stock
- **transactions:** id (PK), product_id (FK), quantity, date, type (sale/purchase)

## Implementation Details
- **JavaFX UI:** Modern forms, tables, dashboards, and navigation
- **DAO Layer:** Encapsulates CRUD, prevents SQL injection, ensures separation of concerns
- **Real-Time Updates:** Observable lists for instant UI refresh
- **Reporting:** Export inventory and sales data to CSV/PDF
- **Testing:** Manual and automated UI and DB tests

## Features
- Add, edit, delete products, suppliers, categories
- Manage and log inventory transactions (sales, purchases)
- Dashboard with live charts for stock and sales trends
- Data export (CSV/PDF)
- User-friendly navigation, error handling, and tooltips

## Testing
- Manual testing of all modules (CRUD, navigation, reporting)
- Automated tests for DAO/database layer
- Cross-verification of report data with direct SQL queries

## Challenges & Solutions
- **Database Access Errors:** Resolved by standardizing configuration, guiding password resets, and clean reinstallation
- **MySQL Permissions:** Addressed by clear documentation and secure installation
- **UI Responsiveness:** Improved with JavaFX threading and observable lists
- **Data Integrity:** Enforced with foreign keys and transaction management

## Team Contributions & Project Division

### Part 1: Project Architecture & Backend (Member 1)
- Designed the overall architecture (MVC, DAO, modular structure)
- Integrated JavaFX with MySQL using JDBC
- Implemented core business logic, stock updates, and transaction management
- Ensured robust connection handling and error management
- **Speaking Points:**
  - Why MVC/DAO was chosen
  - How the backend logic ensures data integrity and performance
  - Challenges with concurrency and multi-user consistency

### Part 2: UI/UX & Dashboards (Member 2)
- Led JavaFX UI design (forms, tables, navigation, dashboards)
- Developed dashboard with live charts for stock/sales analytics
- Enhanced look and feel (color schemes, layouts, icons)
- Added tooltips and onboarding guides
- **Speaking Points:**
  - User experience goals and design choices
  - How charts and dashboards provide business insight
  - Addressing UI responsiveness and feedback

### Part 3: Database & Integration (Member 3)
- Designed MySQL schema (tables, FKs, constraints)
- Wrote SQL scripts for schema and sample data
- Implemented DAOs for all entities (CRUD, analytics)
- Integrated DB operations with JavaFX app
- **Speaking Points:**
  - Schema normalization and data integrity
  - Preventing SQL injection and ensuring efficient queries
  - Troubleshooting integration issues

### Part 4: Reporting & Testing (Member 4)
- Developed reporting modules (inventory, sales, export to CSV/PDF)
- Wrote and executed test cases (manual/automated)
- Ensured data integrity in reports via SQL cross-checks
- Led bug tracking and fixes
- **Speaking Points:**
  - Importance of reporting for business users
  - Testing strategy and sample bugs found/fixed
  - Ensuring report accuracy and usability

### Part 5: Documentation & Presentation (Member 5)
- Authored README, REPORT, and user manuals
- Designed presentation assets (icons, banners, screenshots)
- Coordinated documentation updates with feature changes
- Made project documents visually appealing and professional
- **Speaking Points:**
  - Documentation best practices
  - How good docs improve onboarding and team efficiency
  - Visual design choices for clarity

## How to Run
See the `README.md` for detailed setup and run instructions.

## Conclusion
This project delivers a complete, professional, and maintainable inventory management system. The modular codebase is ready for further enhancements, such as user authentication or cloud deployment, and serves as an excellent foundation for real-world inventory solutions.
- **UI Responsiveness:** Leveraged JavaFX threading for long-running tasks

## 5. Screenshots & Diagrams
![Dashboard](assets/dashboard.png)

---

*Each member can expand their section above for presentations or documentation.*
