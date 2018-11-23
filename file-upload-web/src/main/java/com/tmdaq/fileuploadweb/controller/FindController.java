package com.tmdaq.fileuploadweb.controller;

import com.tmdaq.fileuploadweb.bean.FileEntity;
import com.tmdaq.fileuploadweb.common.PageUtil;
import com.tmdaq.fileuploadweb.common.ResultDo;
import com.tmdaq.fileuploadweb.service.FileEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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
        return findService.scan(Arrays.asList(new String[]{"/home/vttmlin/IdeaProjects/ahjy/out/artifacts/ahjy/WEB-INF"}));
    }

    @GetMapping("/all")
    public ResultDo<PageUtil<FileEntity>> getAll() {
        FileEntity fileEntity = ((FileEntity) new FileEntity().setId(1L));
        ResultDo<PageUtil<FileEntity>> all = findService.findAll(new PageUtil<>(fileEntity));
        return all;
    }
}
