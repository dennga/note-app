package de.lokal.programmierung.notizen;



import de.lokal.programmierung.notizen.model.Note;
import de.lokal.programmierung.notizen.repository.NoteRepository;
import de.lokal.programmierung.notizen.service.NoteService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Aktiviert den Mockito-Turbo
class NoteServiceTest {

    @Mock
    private NoteRepository repository; // Die simulierte Datenbank

    @InjectMocks
    private NoteService service; // Unser Service, in den die Mock-DB "injiziert" wird

    @Test
    void addNote_ShouldSave_WhenContentIsValid() {
        // Arrange
        String validContent = "Meeting vorbereiten";

        // Act
        service.addNote(validContent);

        // Assert
        // Wir prüfen: Wurde die save()-Methode genau 1x aufgerufen?
        verify(repository, times(1)).save(any(Note.class));
    }

    @Test
    void addNote_ShouldIgnore_WhenContentIsBlank() {
        // Arrange
        String blankContent = "   ";

        // Act
        service.addNote(blankContent);

        // Assert
        // Wir prüfen: Wurde save() eh NICHT aufgerufen? (Fail-Fast im Service)
        verify(repository, never()).save(any());
    }

    @Test
    void addNote_ShouldIgnore_WhenContentIsNull() {
        // Act
        service.addNote(null);

        // Assert
        verify(repository, never()).save(any());
    }
}