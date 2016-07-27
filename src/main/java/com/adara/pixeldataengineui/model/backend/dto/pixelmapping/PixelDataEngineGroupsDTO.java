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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((trigger_key_id == null) ? 0 : trigger_key_id.hashCode());
        result = prime * result
                + ((gid == null) ? 0 : gid.hashCode());
        result = prime * result
                + ((group_type == null) ? 0 : group_type.hashCode());

        return result;
    }
}
