package com.tmdaq.fileuploadweb.dao;

import com.tmdaq.fileuploadweb.bean.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileEntryRepository extends JpaRepository<FileEntity,Long>{
}
