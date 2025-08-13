package com.first.exam.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
/**
 * packageName    : com.first.exam.common.entity
 * fileName       : Msg
 * author         : 김지은_N01
 * date           : 25. 8. 12.
 * description    : Front에 전달할 데이터를 담는 메시지 엔티티
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 8. 12.        김지은_N01       최초 생성
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Msg {

    private String msg;
    private Object result;

    public Msg(String msg, Object result) {
        this.msg = msg;
        this.result  = result;
    }
}
