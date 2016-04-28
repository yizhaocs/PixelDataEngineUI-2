package com.adara.pixeldataengineui.model.frontend.requestbody;

/**
 * Created by yzhao on 4/28/16.
 */
public class GroupRequest {
    private Integer keyId;
    private Integer gid;
    private String type;

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }
}