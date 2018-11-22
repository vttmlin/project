package com.tmdaq.fileuploadweb.dao;

import com.tmdaq.fileuploadweb.bean.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.util.List;

public interface FileEntityRepository extends JpaRepository<FileEntity,Long> {

}
