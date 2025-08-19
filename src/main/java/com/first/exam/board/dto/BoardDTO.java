package com.first.exam.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : com.first.exam.board.dto
 * fileName       : BoardDTO
 * author         : 김지은_N01
 * date           : 25. 8. 18.
 * description    : Board 정보를 담을 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 18.        김지은_N01       최초 생성
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {

	private Long seqno;       // 게시글 ID
	private String userId;    // 작성자 ID
	private String username;  // 작성자 이름(선택적으로 User 엔티티에서 꺼내오기)
	private String category;  // 카테고리
	private String title;     // 제목
	private String bigo;      // 내용
}
