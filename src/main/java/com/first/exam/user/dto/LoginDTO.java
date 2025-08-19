package com.first.exam.user.dto;

/**
 * packageName    : com.first.exam.user.dto
 * fileName       : LoginDTO
 * author         : 김지은_N01
 * date           : 25. 8. 18.
 * description    : login 시 아이디와 비밀번호를 담을 DTO
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
public class LoginDTO {
    private String userid;   // 사용자 ID
    private String password; // 비밀번호
}
