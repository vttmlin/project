package com.tmdaq.fileuploadweb.controller;

import com.tmdaq.fileuploadweb.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/find")
public class FindController {
    final
    FindService findService;

    @Autowired
    public FindController(FindService findService) {
        this.findService = findService;
    }
}
