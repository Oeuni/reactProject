package com.first.exam.user.entity;

import com.first.exam.board.entity.Board;
import com.first.exam.common.entity.BaseTimeEntity;
import com.first.exam.user.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.first.exam.user.entity
 * fileName       : User
 * author         : 김지은_N01
 * date           : 25. 8. 13.
 * description    : User 엔티티
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
// @Builder : 빌더 패턴 User.builder() 사용 가능
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "userid", length = 20)
    private String userid;          // user ID

    @Column(nullable = false, length = 20)
    private String usernm;          // 이름

    @Column(nullable = false, length = 30)
    private String password;        // 비밀번호

    @Column(nullable = false, length = 1)
    private String adminyn;         // 관리자여부

    // 1명당 여러개의 글 작성 가능
    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    public static User fromDTO(UserDTO dto) {
        return User.builder()
                .userid(dto.getUserid())
                .usernm(dto.getUsernm())
                .password(dto.getPassword())
                .adminyn(dto.getAdminyn())
                .build();
    }
}
