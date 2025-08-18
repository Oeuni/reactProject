package com.first.exam.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
/**
 * packageName    : com.first.exam.common.entity
 * fileName       : Msg
 * author         : 김지은_N01
 * date           : 25. 8. 13.
 * description    : 자식 클래스에 등록&수정 시간 제공
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 13.        김지은_N01       최초 생성
 */
@EntityListeners(value = {AuditingEntityListener.class})    // 감사 기능 적용
@MappedSuperclass   //자식 클래스에 매핑 정보만 제공하기 위해 사용
@Getter
@Setter
public abstract class BaseTimeEntity {

    @CreatedDate                    // 생성되어 저장될 때 시간을 자동으로 저장
    @Column(updatable = false)      // 수정 불가능
    private LocalDateTime regTime;

    @LastModifiedDate               // 값이 변경될 때 시간을 자동으로 저장
    private LocalDateTime updateTime;
}
