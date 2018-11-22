package com.tmdaq.fileuploadweb.controller;

import com.tmdaq.fileuploadweb.bean.FileEntity;
import com.tmdaq.fileuploadweb.common.ResultDo;
import com.tmdaq.fileuploadweb.service.FileEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/find")
public class FindController {
    private final FileEntryService findService;

    @Autowired
    public FindController(FileEntryService findService) {
        this.findService = findService;
    }

    @GetMapping
    public ResultDo<FileEntity> insrt() {
        return findService.scan(null);
    }
}
