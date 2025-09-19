package com.first.exam.board.controller;

import com.first.exam.board.dto.BoardDTO;
import com.first.exam.board.dto.TestDto;
import com.first.exam.board.service.BoardService;
import com.first.exam.common.entity.Msg;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.first.exam.board.controller
 * fileName       : BoardController
 * author         : 김지은_N01
 * date           : 25. 8. 18.
 * description    : 게시글 관련 요청을 주고받는 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 18.        김지은_N01        최초 생성
 * 25. 8. 19.        김지은_N01        CRUD 작성
 */

@Slf4j
@RequiredArgsConstructor
@RestController
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//@CrossOrigin(origins = "https://13.53.74.46", allowCredentials = "true")
@CrossOrigin(origins = "https://oeun.shop", allowCredentials = "true")
public class BoardController {

    private final BoardService boardService;

    // 게시글 등록
    @PostMapping("/board")
    public ResponseEntity<Msg> createBoard(@RequestBody BoardDTO boardDTO,
                                           HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Msg("로그인이 필요합니다.", false));
        }

        String userId = (String) session.getAttribute("userId");

        boolean result = boardService.saveBoard(boardDTO, userId);

        return ResponseEntity.ok(new Msg(result ? "게시글이 성공적으로 등록되었습니다." : "게시글 등록에 실패하였습니다.", result));
    }

    // 모든 게시글 조회
    @GetMapping("/board")
    public ResponseEntity<List<BoardDTO>> read() {
        List<BoardDTO> boardDTOList = boardService.getBoardList();
        return ResponseEntity.ok(boardDTOList);
    }

    // @PathVariable : 경로 변수
    // 특정 게시글 조회
    @GetMapping("/board/{seqno}")
    public ResponseEntity<BoardDTO> readSeqno(@PathVariable("seqno") Long seqno) {
        BoardDTO board = boardService.getBoardBySeqno(seqno);
        return ResponseEntity.ok(board);
    }

    // 게시글 권한 확인
    @GetMapping("/board/{seqno}/check")
    public ResponseEntity<Msg> checkBoardPermission(@PathVariable("seqno") Long seqno,
                                                    HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Msg("로그인이 필요합니다.", false));
        }

        String sessionUserId = (String) session.getAttribute("userId");

        boolean hasPermission = boardService.checkUser(seqno, sessionUserId);

        return ResponseEntity.ok(new Msg(hasPermission ? "" : "게시글 권한이 없습니다.", hasPermission));
    }

    // 게시글 수정 요청
    @PutMapping("/board/{seqno}")
    public ResponseEntity<Msg> modify(@RequestBody BoardDTO boardDTO, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Msg("로그인이 필요합니다.", false));
        }

        String sessionUserId = (String) session.getAttribute("userId");

        boolean result = boardService.modifyBoard(boardDTO, sessionUserId);
        return ResponseEntity.ok(new Msg(result ? "게시글이 성공적으로 수정되었습니다." : "게시글 수정에 실패하였습니다.", result));
    }

    // 게시글 삭제
    @DeleteMapping("/board/{seqno}")
    public ResponseEntity<Msg> remove(@PathVariable("seqno") Long seqno, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Msg("로그인이 필요합니다.", false));
        }

        String sessionUserId = (String) session.getAttribute("userId");

        boolean result = boardService.deleteBoard(seqno, sessionUserId);
        return ResponseEntity.ok(new Msg(result ? "게시글이 성공적으로 삭제되었습니다." : "게시글 삭제에 실패하였습니다.", result));
    }

    // 로그인 여부 확인
    @GetMapping("/auth/check")
    public ResponseEntity<Map<String, Object>> checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Map<String, Object> response = new HashMap<>();
        if (session != null && session.getAttribute("userId") != null) {
            response.put("loggedIn", true);
            response.put("userId", session.getAttribute("userId"));
        } else {
            response.put("loggedIn", false);
        }
        return ResponseEntity.ok(response);
    }

}
