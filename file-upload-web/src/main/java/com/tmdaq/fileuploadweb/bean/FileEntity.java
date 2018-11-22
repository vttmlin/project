package com.tmdaq.fileuploadweb.bean;

import com.tmdaq.fileuploadweb.common.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import sun.rmi.runtime.Log;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_file")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FileEntity extends BaseBean{
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_size")
    private Long fileSize;
    @Column(name = "fize_path")
    private String filePath;
    @Column(name = "update_count")
    private Long updateCount;
    @Column(name = "is_dir")
    private Boolean isDir;
    @Column(name = "is_hidden")
    private Boolean isHidden;
    @Column(name = "is_write")
    private Boolean isWrite;

}
