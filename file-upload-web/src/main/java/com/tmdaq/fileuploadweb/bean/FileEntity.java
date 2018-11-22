package com.tmdaq.fileuploadweb.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_file")
@Entity
@Data
@Accessors(chain = true)
public class FileEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String fileName;
    @Column
    private Long fileSize;
    @Column
    private String filePath;
    @Column
    private Date createTime;
    @Column
    private Date updateTime;
    @Column
    private Long updateCount;
    @Column
    private Boolean isDir;
    @Column
    private Boolean isHidden;
    @Column
    private Boolean isWrite;
    @Column
    private boolean isDelete;
    @Transient
    private String createTimeStart;
    @Transient
    private String createTimeEnd;
    @Transient
    private String updateTimeStart;
    @Transient
    private String updateTimeEnd;
}
