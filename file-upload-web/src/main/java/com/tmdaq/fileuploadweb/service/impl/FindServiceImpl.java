package com.tmdaq.fileuploadweb.service.impl;

import com.tmdaq.fileuploadweb.bean.FileEntity;
import com.tmdaq.fileuploadweb.dao.FileEntityRepository;
import com.tmdaq.fileuploadweb.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindServiceImpl implements FindService{
    final FileEntityRepository repository;

    @Autowired
    public FindServiceImpl(FileEntityRepository repository) {
        this.repository = repository;
    }

    public List<FileEntity> findAll() {
        return repository.findAll();
    }
}
