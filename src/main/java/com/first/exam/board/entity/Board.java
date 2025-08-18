package com.first.exam.board.entity;

import com.first.exam.common.entity.BaseTimeEntity;
import com.first.exam.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

/**
 * packageName    : com.first.exam.board.entity
 * fileName       : Board
 * author         : 김지은_N01
 * date           : 25. 8. 13.
 * description    : Board 엔티티
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 13.        김지은_N01       최초 생성
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "seqno")
    private Long seqno;                                 // 게시글ID(순번)

    @ManyToOne(fetch = FetchType.LAZY)                  // 지연로딩
    @JoinColumn(name = "userid", nullable = false)      // DB 컬럼명 (FK설정)
    private User user;

    @Column(length = 20)
    private String category;                            // 글 카테고리

    @Column(nullable = false, length = 100)
    private String title;                               // 제목

    @Column(columnDefinition = "TEXT", nullable = false)
    private String bigo;                                // 내용
}
