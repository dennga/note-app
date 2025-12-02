# 🚀 High-Performance Team Notizen App

Eine Cloud-Native Webanwendung, entwickelt mit Fokus auf Performance, Thread-Safety und Clean Architecture.

## ✨ Features

* **High Performance:** In-Memory Database (H2) und Server-Side Rendering für minimale Latenz.
* **Concurrency:** Thread-sichere Implementierung für gleichzeitige Zugriffe.
* **Modern UI:** GPU-beschleunigte CSS-Animationen ohne externe Frameworks (Zero-Bloat).
* **Robustheit:** Fail-Fast Validierung und Exception Handling.
* **DevOps Ready:** Docker-Containerisierung auf Basis von Alpine Linux.

## 🛠 Tech Stack

* **Java 21** (LTS)
* **Spring Boot 3** (Web, Data JPA, Validation)
* **Thymeleaf** (Frontend)
* **H2 Database** (SQL)
* **Docker** (Infrastructure)

---

## 📊 Architektur & Datenfluss

Das folgende Sequenzdiagramm zeigt den Weg einer Datenanfrage durch die Schichten (MVC-Pattern):

```mermaid
sequenceDiagram
    autonumber
    participant User
    participant Browser
    participant Controller as NoteController
    participant Service as NoteService
    participant Repo as NoteRepository
    participant DB as H2 Database

    Note right of User: User erstellt neue Notiz
    User->>Browser: Tippt Text & Klickt "Speichern"
    Browser->>Controller: POST /add (content="Meeting")
    
    activate Controller
    Controller->>Service: addNote("Meeting")
    
    activate Service
    Service->>Service: validate(content)
    alt Content is valid
        Service->>Repo: save(new Note)
        activate Repo
        Repo->>DB: INSERT INTO note ...
        DB-->>Repo: (Bestätigung)
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
    Browser-->>User: Zeigt aktualisierte Liste
