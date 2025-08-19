package com.first.exam.board.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.first.exam.board.dto.TestDto;
import com.first.exam.common.entity.Msg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.first.exam.board.controller
 * fileName       : TestController
 * author         : 김지은_N01
 * date           : 25. 8. 12.
 * description    : 리액트 연결 테스트용 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 12.        김지은_N01       최초 생성
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<Msg> sendSms(@RequestBody TestDto testDto) {
        String s = testDto.getAnswer();
        log.info("입력 : " + s);
        if(s.equals("김지은")) {
            return ResponseEntity.ok().body(new Msg("사용자가 맞습니다!", true));
        }
        else {
            return ResponseEntity.ok().body(new Msg("사용자가 아니에요.", false));
        }
    }
}
