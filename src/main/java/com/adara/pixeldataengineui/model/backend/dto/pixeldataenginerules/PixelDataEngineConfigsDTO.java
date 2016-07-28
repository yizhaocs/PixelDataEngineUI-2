package com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class PixelDataEngineConfigsDTO extends BaseDTO implements Serializable {
    private String gid;
    private String key_id;
    private String priority;
    private String type;
    private String parse_rule;
    private String condition_rule;
    private String action_rule;

    public PixelDataEngineConfigsDTO() {

    }

    public PixelDataEngineConfigsDTO(String gid, String key_id, String priority, String type, String parse_rule, String condition_rule, String action_rule) {
        this.gid = gid;
        this.key_id = key_id;
        this.priority = priority;
        this.type = type;
        this.parse_rule = parse_rule;
        this.condition_rule = condition_rule;
        this.action_rule = action_rule;
    }

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

    @Override
    public String toString() {
        return "PixelDataEngineConfigsDTO [gid=" + gid + ", key_id=" + key_id
                + ", priority=" + priority + ", type=" + type + ", parse_rule="
                + parse_rule + ", condition_rule=" + condition_rule + ", action_rule="
                + action_rule + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((gid == null) ? 0 : gid.hashCode());
        result = prime * result + ((key_id == null) ? 0 : key_id.hashCode());
        result = prime * result + ((priority == null) ? 0 : priority.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((parse_rule == null) ? 0 : parse_rule.hashCode());
        result = prime * result + ((condition_rule == null) ? 0 : condition_rule.hashCode());
        result = prime * result + ((action_rule == null) ? 0 : action_rule.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PixelDataEngineConfigsDTO other = (PixelDataEngineConfigsDTO) obj;
        if (gid == null) {
            if (other.gid != null) {
                return false;
            }
        } else if (!gid.equals(other.gid)) {
            return false;
        }
        if (key_id == null) {
            if (other.key_id != null) {
                return false;
            }
        } else if (!key_id.equals(other.key_id)) {
            return false;
        }
        if (priority == null) {
            if (other.priority != null) {
                return false;
            }
        } else if (!priority.equals(other.priority)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type
                .equals(other.type)) {
            return false;
        }
        if (parse_rule == null) {
            if (other.parse_rule != null) {
                return false;
            }
        } else if (!parse_rule.equals(other.parse_rule)) {
            return false;
        }
        if (condition_rule == null) {
            if (other.condition_rule != null) {
                return false;
            }
        } else if (!condition_rule.equals(other.condition_rule)) {
            return false;
        }
        if (action_rule == null) {
            if (other.action_rule != null) {
                return false;
            }
        } else if (!action_rule.equals(other.action_rule)) {
            return false;
        }

        return true;
    }

}
