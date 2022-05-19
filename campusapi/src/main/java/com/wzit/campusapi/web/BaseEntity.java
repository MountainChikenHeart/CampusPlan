package com.wzit.campusapi.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 实体层基类
 * @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  不返回实体
 */
public class BaseEntity {

    /**
     * 主键ID
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long keyId;

    /**
     * 设备唯一识别码 MD5值
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String deviceNum;

    /**
     * 当前页码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int pageNum;

    /**
     * 每页数量
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int pageSize;

    /**
     * appid
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String appId;

    /**
     * appkey
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String appKey;

    /**
     * securitykey
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String securityKey;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * satoken
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String saToken;


    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public String getSaToken() {
        return saToken;
    }

    public void setSaToken(String saToken) {
        this.saToken = saToken;
    }
}
