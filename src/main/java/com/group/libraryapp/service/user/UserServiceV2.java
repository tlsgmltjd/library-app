package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest request) {
        // save 메소드에 객체를 넣어주면 INSERT SQL이 자동으로 날라간다.
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    public List<UserResponse> getUsers() {
        // findAll 을 사용하면 모든 데이터를 가져온다.
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(user.getId(),user.getName(),user.getAge()))
                .collect(Collectors.toList());
    }

    public void updateUser(UserUpdateRequest request) {
        // select * from user where id = ?;
        // Optional<User>
        User user = userRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
        userRepository.save(user);
    }

    public void deleteUser(String name) {
        // select * from user where name = ?;
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new IllegalArgumentException();
        }

        userRepository.delete(user);
    }
}
