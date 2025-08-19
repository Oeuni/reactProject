package com.first.exam.board.repository;

import com.first.exam.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.first.exam.board.repository
 * fileName       : BoardRepository
 * author         : 김지은_N01
 * date           : 25. 8. 18.
 * description    : Board 테이블 관련 DB 동작
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 18.        김지은_N01       최초 생성
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

}
