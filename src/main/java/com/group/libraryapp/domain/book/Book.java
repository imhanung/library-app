package com.group.libraryapp.domain.book;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    protected Book(){
    }

    @Column(nullable = false, length = 255, name ="name")
    private String name;

    public Book(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Book name cannot be null or blank");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
