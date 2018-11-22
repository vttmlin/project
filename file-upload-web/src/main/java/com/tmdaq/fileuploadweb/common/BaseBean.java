package com.tmdaq.fileuploadweb.common;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
@Accessors(chain = true)
public class BaseBean implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "is_delete")
    private Boolean isDelete = false;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Transient
    private String createTimeStart;
    @Transient
    private String createTimeEnd;
    @Transient
    private String updateTimeStart;
    @Transient
    private String updateTimeEnd;
}
