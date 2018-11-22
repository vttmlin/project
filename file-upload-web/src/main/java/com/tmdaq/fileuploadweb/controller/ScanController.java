package com.tmdaq.fileuploadweb.controller;

import com.tmdaq.fileuploadweb.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scan")
public class ScanController {
    final ScanService scanService;

    @Autowired
    public ScanController(ScanService scanService) {
        this.scanService = scanService;
    }
}
