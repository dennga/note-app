# 🚀  Team Notes App

A cloud-native web application designed with a strong focus on performance, thread safety, and clean architecture.

## ✨ Features

* **High Performance:** Utilizes an in-memory H2 database and server-side rendering (SSR) for minimal latency and fastest Time to First Byte (TTFB).
* **Concurrency:** Thread-safe service implementation using `CopyOnWriteArrayList` (in-memory mode) or optimized JPA transactions for handling concurrent user requests.
* **Modern UI:** Lightweight, GPU-accelerated CSS animations without heavy external frameworks (Zero-Bloat).
* **Robustness:** Implements "Fail-Fast" validation logic and comprehensive exception handling.
* **DevOps Ready:** Fully containerized using a minimalistic Alpine Linux Docker image.

## 🛠 Tech Stack

* **Backend:** Java 21 (LTS), Spring Boot 3 (Web, Data JPA, Validation)
* **Database:** H2 (Embedded SQL)
* **Frontend:** Thymeleaf, CSS3 (Native)
* **DevOps:** Docker, Maven

---

## 📊 Architecture & Data Flow

The following sequence diagram illustrates the request lifecycle through the MVC layers:

```mermaid
sequenceDiagram
    autonumber
    participant User
    participant Browser
    participant Controller as NoteController
    participant Service as NoteService
    participant Repo as NoteRepository
    participant DB as H2 Database

    Note right of User: User creates a new note
    User->>Browser: Types text & clicks "Add"
    Browser->>Controller: POST /add (content="Meeting")
    
    activate Controller
    Controller->>Service: addNote("Meeting")
    
    activate Service
    Service->>Service: validate(content)
    alt Content is valid
        Service->>Repo: save(new Note)
        activate Repo
        Repo->>DB: INSERT INTO note ...
        DB-->>Repo: (Confirmation)
        deactivate Repo
    else Content is empty
        Service-->>Controller: (ignore / fail silent)
    end
    
    Service-->>Controller: (void)
    deactivate Service
    
    Controller-->>Browser: Redirect 302 (/)
    deactivate Controller
    
    Note right of User: Post-Redirect-Get Pattern
    Browser->>Controller: GET /
    activate Controller
    Controller->>Service: getAllNotes()
    Service->>Repo: findAll()
    Repo-->>Service: List<Note>
    Service-->>Controller: List<Note>
    Controller-->>Browser: Render index.html
    deactivate Controller
    Browser-->>User: Displays updated list
