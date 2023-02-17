package com.example.litemanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName task_result
 */
@TableName(value ="task_result")
public class TaskResult implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String shellFileName;

    /**
     * 
     */
    private Date executeTime;

    /**
     * 
     */
    private String javaExecuteResult;

    private String shellExecuteResult;

    /**
     * 
     */
    private Integer version;

    /**
     * 
     */
    private Integer lastUpdateUser;

    /**
     * 
     */
    private Date lastUpdateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "TaskResult{" +
                "id=" + id +
                ", shellFileName='" + shellFileName + '\'' +
                ", executeTime=" + executeTime +
                ", javaExecuteResult='" + javaExecuteResult + '\'' +
                ", shellExecuteResult='" + shellExecuteResult + '\'' +
                ", version=" + version +
                ", lastUpdateUser=" + lastUpdateUser +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShellFileName() {
        return shellFileName;
    }

    public void setShellFileName(String shellFileName) {
        this.shellFileName = shellFileName;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public String getJavaExecuteResult() {
        return javaExecuteResult;
    }

    public void setJavaExecuteResult(String javaExecuteResult) {
        this.javaExecuteResult = javaExecuteResult;
    }

    public String getShellExecuteResult() {
        return shellExecuteResult;
    }

    public void setShellExecuteResult(String shellExecuteResult) {
        this.shellExecuteResult = shellExecuteResult;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(Integer lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}