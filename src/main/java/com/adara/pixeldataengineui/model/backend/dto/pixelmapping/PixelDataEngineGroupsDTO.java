package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class PixelDataEngineGroupsDTO {
    private String trigger_key_id;
    private Integer gid;
    private Integer group_type;

    public String getTrigger_key_id() {
        return trigger_key_id;
    }

    public void setTrigger_key_id(String trigger_key_id) {
        this.trigger_key_id = trigger_key_id;
    }

    public Integer getGroup_type() {
        return group_type;
    }

    public void setGroup_type(Integer group_type) {
        this.group_type = group_type;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }


}
