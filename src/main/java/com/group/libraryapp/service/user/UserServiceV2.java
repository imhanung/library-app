package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.IIOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //아래 있는 함수가 시작될때 스타트 트랜잭션을 함.
    //함수가 예외없이 잘 끝나면 commit, 문제가 있다면 roolback.

    @Transactional
    public void saveUser(UserCreateRequest request)
    {
        User u =userRepository.save(new User(request.getName(), request.getAge())); // 유저 저장기능인 insert sql이 자동으로날악는 기능.
    }
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(user -> new UserResponse(user.getId(), user.getName(),user.getAge()))
                .collect(Collectors.toList());
    }
    @Transactional
    public void updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new); // 유저 리포지토리에 파인트바이아이디를 통해서 아무것도 없다면 elsethrow예외를 던진다.
        user.updateName(request.getName()); // user객체로 예외가 안나오면 수정하고
        userRepository.save(user); //jpa save함수로 수정된 유저 이름저장.
    }
    @Transactional
    public void deleteUser(String name) {
        //FindByName = Select*from user Where name = ?
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);// 원래 존재하는 jpa함수 delete
    }









}
