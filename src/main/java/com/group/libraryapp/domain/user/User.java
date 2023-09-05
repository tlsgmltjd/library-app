package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.userLoanHistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 20, name = "name")
    // 필드이랑 디비 Column 이름이랑 같으면 name 생략 가능
    // 그냥 따로 알려줘야할 조건이 없으면 @Column 어노테이션을 생략 가능하다.
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // 1 : N 관계
    private List<UserLoanHistory> userLoanHistoryList = new ArrayList<>();

    protected User() {}

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookName) {
        this.userLoanHistoryList.add(new UserLoanHistory(this, bookName));
    }
}
