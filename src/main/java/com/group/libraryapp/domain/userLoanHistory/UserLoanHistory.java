package com.group.libraryapp.domain.userLoanHistory;

import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private Long uesrId;

    @Column(nullable = false)
    private String bookName;

    @Column(length = 1)
    // boolean으로 처리해도 tinyint에 잘 매핑된다.
    private boolean isReturn;

    protected UserLoanHistory() {

    }

    public UserLoanHistory(Long uesrId, String bookName) {
        if (bookName == null || bookName.isBlank()) throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", bookName));
        this.uesrId = uesrId;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn() {
        this.isReturn = true;
    }
}
