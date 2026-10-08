package com.anshu.notesapi;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteRepository repository;

    public NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    // 1. List all notes
    @GetMapping
    public List<Note> listNotes() {
        return repository.findAll();
    }

    // 2. Get one note by id (404 if it doesn't exist)
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Create a note (returns 201 Created)
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        note.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(note));
    }
}
