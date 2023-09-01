package com.group.libraryapp.domain.userLoanHistory;

import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private Long user_id;

    @Column(nullable = false)
    private String book_name;

    @Column(length = 1)
    private Short is_return;

    protected UserLoanHistory() {

    }

    public UserLoanHistory(Long user_id, String book_name) {
        if (book_name == null || book_name.isBlank()) throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", book_name));
        this.user_id = user_id;
        this.book_name = book_name;
        this.is_return = 0;
    }

}
