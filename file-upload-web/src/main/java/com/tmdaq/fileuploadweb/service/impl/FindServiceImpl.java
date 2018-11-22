package com.tmdaq.fileuploadweb.service.impl;

import com.tmdaq.fileuploadweb.dao.FileEntityRepository;
import com.tmdaq.fileuploadweb.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindServiceImpl implements FindService{
    final FileEntityRepository repository;

    @Autowired
    public FindServiceImpl(FileEntityRepository repository) {
        this.repository = repository;
    }
}
