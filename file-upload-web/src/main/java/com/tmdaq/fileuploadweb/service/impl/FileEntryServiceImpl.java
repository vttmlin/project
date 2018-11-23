package com.tmdaq.fileuploadweb.service.impl;

import com.tmdaq.fileuploadweb.bean.FileEntity;
import com.tmdaq.fileuploadweb.common.Code;
import com.tmdaq.fileuploadweb.common.PageUtil;
import com.tmdaq.fileuploadweb.common.ResultDo;
import com.tmdaq.fileuploadweb.dao.FileEntryRepository;
import com.tmdaq.fileuploadweb.service.FileEntryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

@Service
@ConfigurationProperties(prefix = "scan")
@Data
@AllArgsConstructor
public class FileEntryServiceImpl implements FileEntryService {
    private final FileEntryRepository repository;
    private List<String> path = new ArrayList<>();
    private Stack<String> dicStack = new Stack<>();

    @Autowired
    public FileEntryServiceImpl(FileEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResultDo<FileEntity> save(FileEntity fileEntity) {
        if (fileEntity == null) {
            return new ResultDo<>(null, Code.ERROR);
        }
        return new ResultDo<>(repository.save(fileEntity), Code.ERROR);
    }

    @Override
    public ResultDo<FileEntity> scan(List<String> path) {
        if (path != null) {
            this.path = path;
        }
        scanFile(path);
        return new ResultDo<>(new FileEntity(), Code.SUCCESS);
    }

    @Override
    public ResultDo<PageUtil<FileEntity>> findAll(PageUtil<FileEntity> pager) {
        Example<FileEntity> example = Example.of(pager.getQuery());
        pager.setSort(new Sort(Sort.Direction.DESC, "id"));
        return new ResultDo<>(pager.setData(repository.findAll(example, pager.toPageRequest()).getContent()));
    }

    private void scanFile(List<String> path) {
        for (String s : path) {
            File file = new File(s);
            if (file.exists()) {
                dicStack.push(file.toString());
            }
        }
        recursion();
    }

    public void recursion() {
        while (!dicStack.isEmpty()) {
            String fileStr = dicStack.pop();
            File file = new File(fileStr);
            if (!file.exists()) {
                return;
            }
            if (file.isFile()) {
                repository.save(fileToFileEntity(file));
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null && file.length() > 0) {
                    for (File f : files) {
                        if (f.isFile()) {
                            repository.save(fileToFileEntity(f));
                        } else {
                            dicStack.push(f.toString());
                        }
                    }
                }
            }
        }
    }

    public FileEntity fileToFileEntity(File file) {
        FileEntity e = ((FileEntity) new FileEntity()
                .setFileName(file.getName())
                .setFilePath(file.getAbsolutePath())
                .setFileSize(file.length())
                .setIsDir(false)
                .setIsWrite(file.canWrite()).setIsHidden(file.isHidden())
                .setCreateTime(new Date(file.lastModified())));
        return e;
    }
}
