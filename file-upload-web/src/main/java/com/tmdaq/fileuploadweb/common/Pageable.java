package com.tmdaq.fileuploadweb.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Pageable<T> {
    public static final Pageable GetFirst = new Pageable<BaseBean>(null);
    private T data;
    private String select = "*";
    @JsonIgnore
    private String orderStr;

    public Pageable(T data) {
        this.data = data;
    }

    private Pageable() {
        this(null);
    }
}
