package de.lokal.programmierung.notizen.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import de.lokal.programmierung.notizen.model.Note;

/*
 * NoteRepository.java
   Data Access Layer interface extending JpaRepository. 
   Provides standardized CRUD operations and abstraction 
   over the H2 SQL database.
 */

public interface NoteRepository extends JpaRepository<Note, UUID> {

}
