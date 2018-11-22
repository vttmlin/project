package com.tmdaq.fileuploadweb.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Code {
    SUCCESS(200, ""), ERROR(500, "");
    private Integer code;
    private String msg;

}
