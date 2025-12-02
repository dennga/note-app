package de.lokal.programmierung.notizen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.lokal.programmierung.notizen.service.NoteService;

/*
NoteController.java
MVC Controller handling HTTP requests. 
Implements the "Post-Redirect-Get" pattern 
to prevent duplicate form submissions and handles 
validation exceptions.
 */

@Controller
public class NoteController {

	private final NoteService noteService;

	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}

	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute("notes", noteService.getAllNotes());
		return "index";
	}

	@PostMapping("/add")
	public String addNote(@RequestParam String content) {
		try {

			noteService.addNote(content);
		} catch (Exception e) {

			System.out.println("Fehler beim Speichern: " + e.getMessage());
		}
		return "redirect:/";
	}
}
