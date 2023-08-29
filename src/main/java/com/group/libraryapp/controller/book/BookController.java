package com.group.libraryapp.controller.book;

import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    // BookService를 스프링 빈으로 등록하면
    // private final BookService bookService = new BookService();
    // 이렇게 인스턴스를 만들 필요 없이
    // 생성자로 바로 넣어줄 수 있다!
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public void saveBook() {
        bookService.saveBook();
    }
}
