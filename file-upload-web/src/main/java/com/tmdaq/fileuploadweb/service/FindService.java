package com.tmdaq.fileuploadweb.service;

import com.tmdaq.fileuploadweb.bean.FileEntity;
import com.tmdaq.fileuploadweb.common.BaseBean;
import com.tmdaq.fileuploadweb.common.Pageable;
import com.tmdaq.fileuploadweb.common.ResultDo;

import java.util.List;

public interface FindService<T extends BaseBean> {

    default ResultDo<T> save(T entity) {
        return new ResultDo<>(entity!=null && entity.getId()!=null?entity:null);
    }

    default ResultDo<T> logicDelete(T entity) {
        return new ResultDo<>(entity!=null && entity.getId()!=null?entity:null);
    }

    default ResultDo<Pageable<List<T>>> findAll(T entity){
        return new ResultDo<>(new Pageable<>(query(entity!=null && entity.getId()!=null?entity:null)));
    }

    default List<T> query(T entity){
        return query(entity,  null);
    }

    @SuppressWarnings("unchecked")
    default ResultDo<T> findById(T entity) {
        return new ResultDo<>(((T) query(entity, Pageable.GetFirst).get(0)));
    }

    List<T> query(T entity, Pageable<T> page);

}
