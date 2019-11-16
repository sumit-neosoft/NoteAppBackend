package com.reactive.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Note {

    @Id
    private String id;

    private String title;

    @Size(max = 999999999)
    private String description;
    private LocalDateTime createdOn;
    private String userId;

    public Note(String id, String title, String description, String userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdOn = LocalDateTime.now();
        this.userId = userId;
    }

    public Note(String title, String description, String userId) {
        this.title = title;
        this.description = description;
        this.createdOn = LocalDateTime.now();
        this.userId = userId;
    }
}
