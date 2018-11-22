package com.tmdaq.fileuploadweb.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Pageable<T> {
    public static final Pageable GetFirst = new Pageable<BaseBean>(null);
    private T data;
    public Pageable(T data) {
        this.data = data;
    }

    private Pageable(){
        this(null);
    }
}
