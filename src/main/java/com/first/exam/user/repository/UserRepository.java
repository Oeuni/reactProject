package com.first.exam.user.repository;

import com.first.exam.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * packageName    : com.first.exam.user.repository
 * fileName       : UserRepository
 * author         : 김지은_N01
 * date           : 25. 8. 18.
 * description    : User 테이블 관련 DB 동작
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 18.        김지은_N01       최초 생성
 */

public interface UserRepository extends JpaRepository<User, String> {

//    @Query("SELECT u FROM user u WHERE u.userid = :userid")
//    User findByUserid(@Param("userid") String userid);          // 사용자 아이디로 사용자 조회

}
