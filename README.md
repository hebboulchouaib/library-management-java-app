# Library Management System (Java, JavaFX, Oracle)

A desktop client-server application for managing a university library: books, students, loans and returns. It is aimed at the librarians of a university.

This project was the practical assignment of the Database 1 module at USTHB (Faculty of Computer Science, second year, academic year 2024/2025). The interface is built with Java and JavaFX, and the data is stored in an Oracle database that the application reaches through JDBC.

**Web version:** I later rebuilt this project as a web application, so that it can be opened from a link without installing Java or Oracle. You can try it here: https://hebboulchouaib.github.io/library-management-web-app/ (source: https://github.com/hebboulchouaib/library-management-web-app).

## Features

**Books**
- Add a book with its title, author, category, publication year and available quantity.
- Edit the details of an existing book.
- Delete a book, with a confirmation before the deletion to avoid accidents.
- Search for a book by title, author or category.

**Students**
- Add, edit and delete a student.
- List all the students registered at the library.

**Loans and returns**
- Register a new loan by choosing the book and the student, and entering the loan date and the authorized duration.
- Register the return of a book, which updates the status of the loan in the database.
- Each loan has a status: in progress, returned or late.

## Database

The database has three tables, as defined by the assignment.

| Table | Columns |
|---|---|
| `Etudiant` | `Num_Etudiant` (primary key), `Nom`, `Prénom`, `Email`, `Téléphone` |
| `Livre` | `Code_Livre` (primary key), `Titre`, `Auteur`, `Catégorie`, `Année_Publication`, `Quantité_Disponible` |
| `Emprunt` | `Num_Emprunt` (primary key), `Num_Etudiant` (foreign key), `Code_Livre` (foreign key), `Date_Emprunt`, `Date_Retour_Prévue`, `Date_Retour_Effective`, `Statut` (En cours, Rendu or En retard) |

## How it is built

- **Language:** Java
- **Interface:** JavaFX
- **Database:** Oracle (SQL)
- **Connection:** JDBC, using the Oracle JDBC driver

The application follows a client-server design. The JavaFX interface is the client, and it talks to the Oracle database through JDBC to read and write books, students and loans.

## Getting started

You need a Java Development Kit, the JavaFX libraries, an Oracle database you can connect to, and the Oracle JDBC driver (`ojdbc`).

1. Clone the repository.
2. In your Oracle database, create the three tables described above.
3. Open the project in your IDE and add the JavaFX libraries and the Oracle JDBC driver to the project.
4. Set the database URL, user name and password where the application opens its JDBC connection.
5. Run the main class.

## Why there is also a web version

This version needs Java and an Oracle database on the machine where it runs, and it has to be compiled before use, which makes it hard to share. The web version keeps the same features and the same three-table design, and moves the data to PostgreSQL on Supabase, so anyone can try it from a browser. It also adds user accounts, where each user has a private library.

## Author

Hebboul abderrahmane Chouaiib, third-year Computer Science student at USTHB, Algiers. [GitHub](https://github.com/hebboulchouaib)
