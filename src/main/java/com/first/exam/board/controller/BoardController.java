package com.first.exam.board.controller;

import com.first.exam.board.dto.TestDto;
import com.first.exam.common.entity.Msg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.first.exam.board.controller
 * fileName       : BoardController
 * author         : 김지은_N01
 * date           : 25. 8. 18.
 * description    : 게시글 관련 처리하는 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 18.        김지은_N01       최초 생성
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {

    // 게시글 등록
    @PostMapping("/board")
    public ResponseEntity<Msg> boardCreate(@RequestBody TestDto testDto) {
        String s = testDto.getAnswer();
        if(s.equals("김지은")) {
            return ResponseEntity.ok().body(new Msg("사용자가 맞습니다!", true));
        }
        else {
            return ResponseEntity.ok().body(new Msg("사용자가 아니에요.", false));
        }
    }

    // 모든 게시글 조회
    @GetMapping("/board")
    public ResponseEntity<Msg> boardAll(@RequestBody TestDto testDto) {
        String s = testDto.getAnswer();
        if(s.equals("김지은")) {
            return ResponseEntity.ok().body(new Msg("사용자가 맞습니다!", true));
        }
        else {
            return ResponseEntity.ok().body(new Msg("사용자가 아니에요.", false));
        }
    }

    // 특정 게시글 조회
    @GetMapping("/board/{seqno}")
    public ResponseEntity<Msg> boardSeqno(@RequestBody TestDto testDto) {
        String s = testDto.getAnswer();
        if(s.equals("김지은")) {
            return ResponseEntity.ok().body(new Msg("사용자가 맞습니다!", true));
        }
        else {
            return ResponseEntity.ok().body(new Msg("사용자가 아니에요.", false));
        }
    }

    // 게시글 수정
    @PutMapping("/board/{seqno}")
    public ResponseEntity<Msg> boardUpdate(@RequestBody TestDto testDto) {
        String s = testDto.getAnswer();
        if(s.equals("김지은")) {
            return ResponseEntity.ok().body(new Msg("사용자가 맞습니다!", true));
        }
        else {
            return ResponseEntity.ok().body(new Msg("사용자가 아니에요.", false));
        }
    }

    // 게시글 삭제
    @DeleteMapping("/board/{seqno}")
    public ResponseEntity<Msg> boardDelete(@RequestBody TestDto testDto) {
        String s = testDto.getAnswer();
        if(s.equals("김지은")) {
            return ResponseEntity.ok().body(new Msg("사용자가 맞습니다!", true));
        }
        else {
            return ResponseEntity.ok().body(new Msg("사용자가 아니에요.", false));
        }
    }
}
