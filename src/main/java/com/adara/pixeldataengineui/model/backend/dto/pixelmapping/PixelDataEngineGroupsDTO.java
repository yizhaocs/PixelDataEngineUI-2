package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class PixelDataEngineGroupsDTO  extends BaseDTO implements Serializable {
    private String trigger_key_id;
    private Integer gid;
    private Integer group_type;

    public PixelDataEngineGroupsDTO(){

    }

    public PixelDataEngineGroupsDTO(String trigger_key_id, Integer gid, Integer group_type){
        this.trigger_key_id = trigger_key_id;
        this.gid = gid;
        this.group_type = group_type;
    }

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

    @Override
    public String toString() {
        return "PixelDataEngineGroupsDTO [trigger_key_id=" + trigger_key_id + ", gid=" + gid
                + ", group_type=" + group_type + "]";
    }

}
