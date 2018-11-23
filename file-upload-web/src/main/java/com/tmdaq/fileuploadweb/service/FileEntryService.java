package com.tmdaq.fileuploadweb.service;

import com.tmdaq.fileuploadweb.bean.FileEntity;
import com.tmdaq.fileuploadweb.common.PageUtil;
import com.tmdaq.fileuploadweb.common.ResultDo;

import java.util.List;

public interface FileEntryService {
    ResultDo<FileEntity> save(FileEntity fileEntity);

    ResultDo<FileEntity> scan(List<String> path);

    ResultDo<PageUtil<FileEntity>> findAll(PageUtil<FileEntity> pager);

}
