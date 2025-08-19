package com.first.exam.board.service;

import com.first.exam.board.dto.BoardDTO;
import com.first.exam.board.entity.Board;
import com.first.exam.board.repository.BoardRepository;
import com.first.exam.user.repository.UserRepository;
import com.first.exam.user.dto.UserDTO;
import com.first.exam.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : com.first.exam.board.service
 * fileName       : BoardService
 * author         : 김지은_N01
 * date           : 25. 8. 18.
 * description    : 게시글 관련 비즈니스 로직을 처리
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 18.        김지은_N01        최초 생성
 * 25. 8. 19.        김지은_N01        CRUD 작성
 */

@Slf4j
@Service
public class BoardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    public BoardService(UserRepository userRepository, BoardRepository boardRepository) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    // 게시글 등록
    public boolean saveBoard(BoardDTO boardDTO, String userId) {

        // User 엔티티 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // DTO → Entity 변환 (User 포함)
        Board board = Board.fromDTO(boardDTO, user);

        // 저장
        Board savedBoard = boardRepository.save(board);

        return true;
    }


    // 모든 게시글 불러오기
    public List<BoardDTO> getBoardList() {
        List<Board> boards = boardRepository.findAll();

        return boards.stream()
                .map(board -> BoardDTO.builder()
                        .seqno(board.getSeqno())
                        .userId(board.getUser().getUserid()) // User 연관관계 있을 경우
                        .username(board.getUser().getUsernm())
                        .category(board.getCategory())
                        .title(board.getTitle())
                        .bigo(board.getBigo())
                        .build())
                .toList();
    }


    // 특정 게시글 불러오기
    public BoardDTO getBoardBySeqno(Long seqno) {
        Board board = boardRepository.findById(seqno)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        return BoardDTO.builder()
                        .seqno(board.getSeqno())
                        .userId(board.getUser().getUserid()) // User 연관관계 있을 경우
                        .username(board.getUser().getUsernm())
                        .category(board.getCategory())
                        .title(board.getTitle())
                        .bigo(board.getBigo())
                        .build();
    }

    // 유저 권한 확인
    public boolean checkUser(Long seqno, String sessionUserId) {
        Board board = boardRepository.findById(seqno)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        String boardUserId = board.getUser().getUserid(); // User 객체에서 아이디 추출

        log.info("================================> 게시글 작성자 : " + boardUserId);
        log.info("================================> 로그인 사용자 : " + sessionUserId);
        return boardUserId.equals(sessionUserId);
    }

    // 게시글 수정
    public boolean modifyBoard(BoardDTO boardDTO, String sessionUserId) {
        Board board = boardRepository.findById(boardDTO.getSeqno())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 권한 체크 - url로 접근 방지
        if (!board.getUser().getUserid().equals(sessionUserId)) {
            throw new RuntimeException("본인 글만 수정할 수 있습니다.");
        }

        // 게시글 수정
        board.setCategory(boardDTO.getCategory());
        board.setTitle(boardDTO.getTitle());
        board.setBigo(boardDTO.getBigo());

        boardRepository.save(board);
        return true;
    }

    // 게시글 삭제
    public boolean deleteBoard(Long seqno, String sessionUserId) {
        Board board = boardRepository.findById(seqno)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 권한 체크 - url로 접근 방지
        if (!board.getUser().getUserid().equals(sessionUserId)) {
            throw new RuntimeException("본인 글만 삭제할 수 있습니다.");
        }

        boardRepository.delete(board);
        return true;
    }
}
