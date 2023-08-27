package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {
    private String name;

    // int -> null 표현 X
    // Integer -> null 표현 가능
    private Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
