📚 University Management System

A scalable Java-based cloud application with modular architecture, custom database design, and real-time analytics.

🚀 Overview

The University Management System is a Java application that manages academic and administrative workflows across a university ecosystem. Built with a modular architecture and backed by a custom relational database, the system supports clean separation of concerns, scalability, and maintainable feature expansion.

The application includes a JavaFX frontend styled with FXML, offering a structured UI layer, and an integrated analytics dashboard for real-time system and user activity tracking.

✨ Key Features
🔹 Modular Architecture

Fully object-oriented Java design.

Clear modules for Students, Courses, Instructors, Enrollment, Programs, and Analytics.

Easy to extend through decoupled services and DAO patterns.

🔹 Custom Database Layer

Relational schema designed from scratch.

Well-defined entity relationships (1:1, 1:N, N:M).

Secure DAO/DataLayer abstraction for all DB operations.

🔹 JavaFX + FXML Frontend

UI built with JavaFX using FXML layouts for clean structure and separation.

Controller classes map UI elements to backend logic.

Responsive dashboard pages for analytics, data management, and navigation.

🔹 Real-Time Analytics Dashboard

CloudWatch-inspired metrics dashboard built into the UI.

Tracks:

Active system users

Enrollment changes

Course/program activity

System events and load indicators

Lightweight update loop for live metric refresh.
