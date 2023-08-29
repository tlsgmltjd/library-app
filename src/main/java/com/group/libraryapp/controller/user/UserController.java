package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    // 1. API의 진입 지점으로써 HTTP Body를 객채로 반환하고 있다. -> Controller
    // 2. 현재 유저가 있는지, 없는지, 등을 확인하고 예외 처리를 해준다. -> Service
    // 3. SQL을 사용하여 실제 DB와의 통신을 담당한다. -> Repository

    // Controller <-> Service <-> Repository

    // Layered Architeture

    private final UserService userService;

    public UserController (JdbcTemplate jdbcTemplate) {
        this.userService = new UserService(jdbcTemplate);
    }

    // C // R // U // D

    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        userService.createUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }


}
