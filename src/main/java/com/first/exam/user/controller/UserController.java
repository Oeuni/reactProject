package com.first.exam.user.controller;

import com.first.exam.board.dto.BoardDTO;
import com.first.exam.common.entity.Msg;
import com.first.exam.user.dto.LoginDTO;
import com.first.exam.user.dto.UserDTO;
import com.first.exam.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.first.exam.user.controller
 * fileName       : UserController
 * author         : 김지은_N01
 * date           : 25. 8. 18.
 * description    : 유저 관련 요청을 주고받는 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 18.        김지은_N01        최초 생성
 * 25. 8. 19.        김지은_N01        세션 추가
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    private final UserService userService;


    /*=========  GET 요청에서 Body 사용 No!!!!  =========*/

    // 아이디 중복확인
    @GetMapping("/user")
    public ResponseEntity<Msg> userCheck(@RequestParam("userid") String userid) {
        boolean result = userService.userCheck(userid);

        return ResponseEntity.ok().body(new Msg(result ? "사용 가능한 아이디입니다." : "이미 사용중인 아이디입니다.", result));
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<Msg> join(@RequestBody UserDTO userDTO) {
        boolean result = userService.join(userDTO);

        return ResponseEntity.ok().body(new Msg(result ? "회원가입이 성공적으로 완료되었습니다." : "회원가입에 실패하였습니다.", result));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Msg> login(@RequestBody LoginDTO loginDTO, HttpServletRequest httpServletRequest) {
        boolean result = userService.login(loginDTO.getUserid(), loginDTO.getPassword());

        // 세션 생성
        if (result) {
            httpServletRequest.getSession().invalidate();
            HttpSession session = httpServletRequest.getSession(true);  // Session이 없으면 생성
            // 세션에 userId를 넣어줌
            session.setAttribute("userId", loginDTO.getUserid());
            session.setMaxInactiveInterval(1800);                          // Session이 30분동안 유지
        }
        return ResponseEntity.ok().body(new Msg(result ? "로그인 성공" : "아이디 또는 비밀번호가 올바르지 않습니다.", result));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Msg> logout(HttpServletRequest request) {

        // 세션 파기
        HttpSession session = request.getSession(false);  // Session이 없으면 null return
        if(session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().body(new Msg("로그아웃됨", true));
    }
}
