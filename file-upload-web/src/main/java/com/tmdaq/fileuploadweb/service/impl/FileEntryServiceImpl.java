package com.tmdaq.fileuploadweb.service.impl;

import com.tmdaq.fileuploadweb.bean.FileEntity;
import com.tmdaq.fileuploadweb.common.Code;
import com.tmdaq.fileuploadweb.common.ResultDo;
import com.tmdaq.fileuploadweb.dao.FileEntryRepository;
import com.tmdaq.fileuploadweb.service.FileEntryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
@ConfigurationProperties(prefix = "scan")
@Data
@AllArgsConstructor
public class FileEntryServiceImpl implements FileEntryService {
    private final FileEntryRepository repository;
    private List<String> path = new ArrayList<>();
    private Integer maxDeep;
    private List<File> unScan = new ArrayList<>();

    @Autowired
    public FileEntryServiceImpl(FileEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResultDo<FileEntity> save(FileEntity fileEntity) {
        if(fileEntity==null){
            return new ResultDo<>(null, Code.ERROR);
        }
        return new ResultDo<>(repository.save(fileEntity), Code.ERROR);
    }

    @Override
    public ResultDo<FileEntity> scan(List<String> path) {
        if(path!=null){
            this.path=path;
        }
        List<FileEntity> list = new LinkedList<>();

        scanFile(path,list);
        return new ResultDo<>(new FileEntity(),Code.SUCCESS);
    }

    private void scanFile(List<String> path,List<FileEntity> list) {
        for (String s : path) {
            digui(new File(s),0,list);
        }
    }

    private void digui(File file,Integer countDeep, List<FileEntity> list) {
        if(!file.exists()){
            return;
        }else if(file.isDirectory()) {
            File[] files = file.listFiles();
            countDeep++;
            if(countDeep>=maxDeep){
                for (File f : files) {
                    digui(f,countDeep,list);
                }
            }else {
                unScan.addAll(Arrays.asList(files));
            }
        }else {
            FileEntity e = ((FileEntity) new FileEntity()
                    .setFileName(file.getName())
                    .setFilePath(file.getAbsolutePath())
                    .setFileSize(file.length())
                    .setIsDir(false)
                    .setIsWrite(file.canWrite()).setIsHidden(file.isHidden())
                    .setCreateTime(new Date(file.lastModified())));
            list.add(e);
        }
    }
}
