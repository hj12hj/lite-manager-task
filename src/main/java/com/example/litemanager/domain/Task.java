package com.example.litemanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName task
 */
@TableName(value ="task")
public class Task implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 
     */
    private String taskName;

    /**
     * 
     */
    private String taskGroup;

    /**
     * 
     */
    private String invokeTarget;

    /**
     * 
     */
    private String cronExpression;

    /**
     * 
     */
    private String misfirePolicy;

    /**
     * 
     */
    private String concurrent;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private Object version;

    /**
     * 
     */
    private Object lastUpdateUser;

    /**
     * 
     */
    private Date lastUpdateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 
     */
    public String getTaskGroup() {
        return taskGroup;
    }

    /**
     * 
     */
    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
    }

    /**
     * 
     */
    public String getInvokeTarget() {
        return invokeTarget;
    }

    /**
     * 
     */
    public void setInvokeTarget(String invokeTarget) {
        this.invokeTarget = invokeTarget;
    }

    /**
     * 
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * 
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * 
     */
    public String getMisfirePolicy() {
        return misfirePolicy;
    }

    /**
     * 
     */
    public void setMisfirePolicy(String misfirePolicy) {
        this.misfirePolicy = misfirePolicy;
    }

    /**
     * 
     */
    public String getConcurrent() {
        return concurrent;
    }

    /**
     * 
     */
    public void setConcurrent(String concurrent) {
        this.concurrent = concurrent;
    }

    /**
     * 
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     */
    public Object getVersion() {
        return version;
    }

    /**
     * 
     */
    public void setVersion(Object version) {
        this.version = version;
    }

    /**
     * 
     */
    public Object getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * 
     */
    public void setLastUpdateUser(Object lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    /**
     * 
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Task other = (Task) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTaskName() == null ? other.getTaskName() == null : this.getTaskName().equals(other.getTaskName()))
            && (this.getTaskGroup() == null ? other.getTaskGroup() == null : this.getTaskGroup().equals(other.getTaskGroup()))
            && (this.getInvokeTarget() == null ? other.getInvokeTarget() == null : this.getInvokeTarget().equals(other.getInvokeTarget()))
            && (this.getCronExpression() == null ? other.getCronExpression() == null : this.getCronExpression().equals(other.getCronExpression()))
            && (this.getMisfirePolicy() == null ? other.getMisfirePolicy() == null : this.getMisfirePolicy().equals(other.getMisfirePolicy()))
            && (this.getConcurrent() == null ? other.getConcurrent() == null : this.getConcurrent().equals(other.getConcurrent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
        result = prime * result + ((getTaskGroup() == null) ? 0 : getTaskGroup().hashCode());
        result = prime * result + ((getInvokeTarget() == null) ? 0 : getInvokeTarget().hashCode());
        result = prime * result + ((getCronExpression() == null) ? 0 : getCronExpression().hashCode());
        result = prime * result + ((getMisfirePolicy() == null) ? 0 : getMisfirePolicy().hashCode());
        result = prime * result + ((getConcurrent() == null) ? 0 : getConcurrent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskName=").append(taskName);
        sb.append(", taskGroup=").append(taskGroup);
        sb.append(", invokeTarget=").append(invokeTarget);
        sb.append(", cronExpression=").append(cronExpression);
        sb.append(", misfirePolicy=").append(misfirePolicy);
        sb.append(", concurrent=").append(concurrent);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", lastUpdateUser=").append(lastUpdateUser);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}