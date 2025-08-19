package com.first.exam.user.service;

import com.first.exam.user.dto.UserDTO;
import com.first.exam.user.entity.User;
import com.first.exam.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.first.exam.user.service
 * fileName       : UserService
 * author         : 김지은_N01
 * date           : 25. 8. 18.
 * description    : 유저 관련 비즈니스 로직을 처리
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 18.        김지은_N01       최초 생성
 */

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 아이디 중복확인
    public boolean userCheck(String userid) {

        if (userid == null) return false;
        User user = userRepository.findById(userid).orElse(null);

        return user == null;
    }

    // 회원가입
    public boolean join(UserDTO userDTO) {

        if (userDTO == null) return false;

        // DTO → Entity 변환
        User user = User.fromDTO(userDTO);

        User savedUser = userRepository.save(user);

        UserDTO savedDTO = UserDTO.builder()
                .userid(savedUser.getUserid())
                .usernm(savedUser.getUsernm())
                .password(savedUser.getPassword())
                //.adminyn("N")
                .build();

        return savedDTO != null;
    }

    // 로그인
    public boolean login(String userid, String password) {
        User user = userRepository.findById(userid).orElse(null);
        if (user == null) return false;

        // 비밀번호 비교
        return user.getPassword().equals(password);
    }
}
