package com.reactive.service;

import com.reactive.feignClients.MailService;
import com.reactive.model.Note;
import com.reactive.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private MailService mailService;

    public Flux<Note> getAllNotes(String id) {
        return this.noteRepository.findAllByUserId(id);
    }

    public Mono<Note> saveNotes(Note note) {
       System.out.println(note);
        if (note.getId() != null) {
            Note noteCreated = new Note(note.getId(), note.getTitle(), note.getDescription(), note.getUserId());
            return this.noteRepository.save(noteCreated);
        }
        else {
            Note noteCreated = new Note(note.getTitle(), note.getDescription(), note.getUserId());
            return this.noteRepository.save(noteCreated);
        }
    }

    public Mono<Note> findById(String id) {
        return noteRepository.findById(id);
    }

    public Mono<String> mailSent() {
        return mailService.createNote().map( s-> "reactive feign with param! : " + s);
    }
}
