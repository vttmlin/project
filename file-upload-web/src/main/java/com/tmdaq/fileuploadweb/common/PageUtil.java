package com.tmdaq.fileuploadweb.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageUtil<T> {
    @JsonIgnore
    public static final PageUtil GetFirst = new PageUtil<BaseBean>();
    private List<T> data;
    private T query;
    @JsonIgnore
    private String select = "*";
    @JsonIgnore
    private String orderStr;
    private Long count;
    private Integer page = 1;
    private Integer size = 10;
    private Sort sort;


    public PageUtil(List<T> data) {
        this.data = data;
    }

    public PageUtil(T query) {
        this.query = query;
    }

    private PageUtil() {
    }

    public PageRequest toPageRequest() {
        if (getSort() == null) {
            return PageRequest.of(this.getPage() - 1, this.size);
        } else {
            return PageRequest.of(this.getPage() - 1, this.size, sort);
        }
    }
}
