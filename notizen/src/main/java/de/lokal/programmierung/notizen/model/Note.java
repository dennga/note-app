package de.lokal.programmierung.notizen.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

/*
 Note.java (Entity)
 A lightweight JPA entity representing a note record. 
 Utilizes UUIDs for distributed system compatibility 
 and Bean Validation constraints for data integrity.
  */

@Entity
public class Note {

	@Id
	private UUID id;

	@NotBlank(message = "der inhalt darf nicht leer sein")
	private String content;
	private long timestamp;

	protected Note() {
	}

	public Note(String content) {
		this.id = UUID.randomUUID();
		this.content = content;

		this.timestamp = System.currentTimeMillis();
	}

	public UUID getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public long getTimestamp() {
		return timestamp;
	}
}