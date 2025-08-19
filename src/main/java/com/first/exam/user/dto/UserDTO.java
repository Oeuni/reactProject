package com.first.exam.user.dto;

/**
 * packageName    : com.first.exam.user.dto
 * fileName       : UserDTO
 * author         : 김지은_N01
 * date           : 25. 8. 18.
 * description    : User 정보를 담을 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 18.        김지은_N01       최초 생성
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String userid;   // 사용자 ID
    private String usernm;   // 이름
    private String password; // 비밀번호
    private String adminyn;  // 관리자 여부 (Y/N)
}
