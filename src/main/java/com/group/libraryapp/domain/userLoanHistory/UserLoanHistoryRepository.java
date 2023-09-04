package com.group.libraryapp.domain.userLoanHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
    // selete * from user_loan_history where name = ? and is_return = ?;
    boolean existsByBookNameAndIsReturn(String bookName, boolean isReturn);

    Optional<UserLoanHistory> findByBookNameAndUserId(String bookName, Long userId);
}
