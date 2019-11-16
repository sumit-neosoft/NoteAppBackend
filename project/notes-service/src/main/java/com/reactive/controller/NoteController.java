package com.reactive.controller;

import com.reactive.feignClients.MailService;
import com.reactive.model.Note;
import com.reactive.service.NoteService;
import com.sun.jdi.event.StepEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class NoteController {

    @Autowired private NoteService noteService;

    @Autowired
    private MailService reactiveMailServiceClient;


    @GetMapping("/getAllNotes/{id}")
    public Flux<Note> getAllItems(@PathVariable("id") String id) {
        return noteService.getAllNotes(id);
    }

    @PostMapping("/")
    public Mono<ResponseEntity<Note>> saveNote(@Valid @RequestBody Note note) {
        Disposable log = this.noteService.mailSent().log("kakss").subscribe(System.out::println);
        System.out.println(log);
        return noteService
                .saveNotes(note)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    @PutMapping("/")
    public Mono<ResponseEntity<Note>> updateNote(@Valid @RequestBody Note note) {
        return noteService.findById(note.getId())
                .flatMap(existingNote -> {
                    existingNote.setTitle(note.getTitle());
                    existingNote.setDescription(note.getDescription());
                    existingNote.setCreatedOn(note.getCreatedOn());
                    return noteService.saveNotes(existingNote);
                })
                .map(updatedNote -> new ResponseEntity<>(updatedNote, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



}
