package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class PixelDataEngineConfigsDTO {
    private String gid;
    private String key_id;
    private String priority;
    private String type;
    private String parse_rule;
    private String condition_rule;
    private String action_rule;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParse_rule() {
        return parse_rule;
    }

    public void setParse_rule(String parse_rule) {
        this.parse_rule = parse_rule;
    }

    public String getCondition_rule() {
        return condition_rule;
    }

    public void setCondition_rule(String condition_rule) {
        this.condition_rule = condition_rule;
    }

    public String getAction_rule() {
        return action_rule;
    }

    public void setAction_rule(String action_rule) {
        this.action_rule = action_rule;
    }
}
