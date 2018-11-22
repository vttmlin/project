package com.tmdaq.fileuploadweb.controller;

import com.tmdaq.fileuploadweb.bean.FileEntity;
import com.tmdaq.fileuploadweb.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/find")
public class FindController {
    private final FindService findService;

    @Autowired
    public FindController(FindService findService) {
        this.findService = findService;
    }

    @GetMapping
    public List<FileEntity> findAll(){
        return findService.findAll();
    }
}
