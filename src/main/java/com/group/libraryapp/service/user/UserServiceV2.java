package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 트랜잭션 등록
    // 아래 있는 함수가 실행될 때 start transaction을 해준다.
    // 함수가 예외 없이 잘 끝났다면 commit
    // 혹시라도 문제가 있다면 rollback
    @Transactional
    public void saveUser(UserCreateRequest request) {
        // save 메소드에 객체를 넣어주면 INSERT SQL이 자동으로 날라간다.
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    // SELECT 쿼리만 사용한다면, readOnly 옵션을 쓸 수 있다.
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        // findAll 을 사용하면 모든 데이터를 가져온다.
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(user.getId(),user.getName(),user.getAge()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        // select * from user where id = ?;
        // Optional<User>
        User user = userRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
        // userRepository.save(user); <- 영속성 컨텍스트가 엔티티의 변경을 감지해 자동으로 저장해줌
    }

    @Transactional
    public void deleteUser(String bookName) {
        // select * from user where name = ?;

        User user = userRepository.findByName(bookName).orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }
}
