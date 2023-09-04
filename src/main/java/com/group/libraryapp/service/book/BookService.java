package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.userLoanHistory.UserLoanHistory;
import com.group.libraryapp.domain.userLoanHistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository,  UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 유저 아이디를 가져위해 유저 객체를 불러옴, 유저가 없을 시 예외처리
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        // 책 이름이 존재하는 책인지 확인, 책이 없을 시 예외처리
        bookRepository.findByName(request.getBookName()).orElseThrow(IllegalArgumentException::new);
        // 대출기록 정보를 가져옴, 대출중일시 예외처리
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(request.getBookName(), false)) {
            throw new IllegalArgumentException("대출되어있는 책입니다.");
        }

        userLoanHistoryRepository.save(new UserLoanHistory(user, request.getBookName()));
    };

    @Transactional
    public void returnBook(BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        UserLoanHistory history = userLoanHistoryRepository.findByBookNameAndUserId(request.getBookName(), user.getId()).orElseThrow(IllegalArgumentException::new);
        history.doReturn();
        // userLoanHistoryRepository.save(history);
        // @Transaction 어노테이션의 영속성 컨텍스트의 변경감지 기능 덕분에 save 안해도 됨
    }
}
