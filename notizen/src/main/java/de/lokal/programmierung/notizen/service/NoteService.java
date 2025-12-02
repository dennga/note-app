package de.lokal.programmierung.notizen.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.lokal.programmierung.notizen.model.Note;
import de.lokal.programmierung.notizen.repository.NoteRepository;

/*
NoteService.java
Encapsulates the business logic. 
Manages data flow between the controller 
and the persistence layer, 
ensuring clean separation of concerns.
 */

@Service
public class NoteService {

	private final NoteRepository repository;

	public NoteService(NoteRepository repository) {
		this.repository = repository;
	}

	public void addNote(String content) {
		if (content != null && !content.trim().isEmpty()) {

			repository.save(new Note(content));
		}
	}

	public List<Note> getAllNotes() {

		return repository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
	}
}