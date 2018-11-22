package com.tmdaq.fileuploadweb.common;

import lombok.Data;


@Data
public class ResultDo<T> {
    private T data;
    private Code code;

    public ResultDo(T data) {
        if (data != null) {
            code = Code.SUCCESS;
        } else {
            code = Code.ERROR;
        }
        this.data = data;
    }

    public ResultDo(T data, Code code) {
        this.code = code;
        this.data = data;
    }
}
