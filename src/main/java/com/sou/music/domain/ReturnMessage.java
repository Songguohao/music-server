package com.sou.music.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnMessage<T> {

    private int code;
    private String msg;
    private T data;

    public static <T> ReturnMessage<T> ok(String msg, T data) {
        int okCode = 1;
        return new ReturnMessage<>(okCode, msg, data);
    }

    public static <T> ReturnMessage<T> error(String msg) {
        int errorCode = 0;
        return new ReturnMessage<>(errorCode, msg, null);
    }
}
