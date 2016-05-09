package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class PixelDataEngineGroupsDTO {
    private Integer triggering_key_id;
    private Integer gid;
    private String group_type;

    public Integer getTriggering_key_id() {
        return triggering_key_id;
    }

    public void setTriggering_key_id(Integer triggering_key_id) {
        this.triggering_key_id = triggering_key_id;
    }

    public String getGroup_type() {
        return group_type;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }


}
