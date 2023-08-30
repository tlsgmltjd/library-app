package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    // 1. API의 진입 지점으로써 HTTP Body를 객채로 반환하고 있다. -> Controller
    // 2. 현재 유저가 있는지, 없는지, 등을 확인하고 예외 처리를 해준다. -> Service
    // 3. SQL을 사용하여 실제 DB와의 통신을 담당한다. -> Repository

    // Controller <-> Service <-> Repository

    // Layered Architeture

    private final UserServiceV2 userService;

    // UserController가 JdbcTemplate에 의존하고 있다.

    // @RestController 어노테이션은 UserController 클래스를 스프링 빈으로 등록한다!

    // 서버가 실행되면 스프링 서버 내부에 거대한 컨테이너를 만들게 된다.
    // 컨테이너 안에는 클래스가 들어가게 된다. (인스턴스화)
    // 스프링 컨테이너 안에 들어간 클래스를 스프링 빈이라고 한다.
    // JdbcTemplate도 스프링 빈으로 등록되어 있다.
    // UserController를 인스턴스할 때 JdbcTemplate를 넣어준다!

    // 스프링 컨테이너는 서로 필요한 클래스끼리 연결해준다.

    // 스프링 빈을 주입 받는 방법
    // 1) 생성자 사용 / @Autowired
    public UserController (UserServiceV2 userService) {
        this.userService = userService;
    }

    // 2) setter / @Autowired
    //    @Autowired
    //    public void serUserService(UserService userService) {
    //        this.userService = userService;
    //    }

    // 3) 필드에 바로 사용
    //    @Autowired
    //    private UserService userService;

    // C // R // U // D

    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
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
