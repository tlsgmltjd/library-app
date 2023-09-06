package com.group.libraryapp.dto.book.response;

public class BookResponse {
    private long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BookResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
