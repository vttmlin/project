package com.tmdaq.fileuploadweb.service.impl;

import com.tmdaq.fileuploadweb.dao.FileEntityRepository;
import com.tmdaq.fileuploadweb.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanServiceImpl implements ScanService{
    final FileEntityRepository repository;

    @Autowired
    public ScanServiceImpl(FileEntityRepository repository) {
        this.repository = repository;
    }
}
