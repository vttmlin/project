package com.tmdaq.fileuploadweb.service.impl;

import com.tmdaq.fileuploadweb.bean.FileEntity;
import com.tmdaq.fileuploadweb.common.Pageable;
import com.tmdaq.fileuploadweb.common.ResultDo;
import com.tmdaq.fileuploadweb.dao.FileEntityRepository;
import com.tmdaq.fileuploadweb.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class FindServiceImpl implements FindService<FileEntity> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FindServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FileEntity> query(@NotNull FileEntity entity, Pageable<FileEntity> pageable) {
        StringBuilder sql = new StringBuilder(256)
                .append("select * from ")
                .append(entity.getClass().getAnnotation(Table.class).name())
                .append("where 1=1 ");
        if(!StringUtils.isEmpty(entity.getFileName())){
            sql.append(" and file_name = '").append(entity.getFileName()).append("'");
        }
        if(!StringUtils.isEmpty(entity.getFileName())){
            sql.append(" and file_name = '").append(entity.getFileName()).append("'");
        }

        return jdbcTemplate.query(sql.toString(),new BeanPropertyRowMapper(FileEntity.class));
    }
}
