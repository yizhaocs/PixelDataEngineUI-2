package com.adara.pixeldataengineui.util.model;

import com.adara.pixeldataengineui.util.rule.action.Action;
import com.adara.pixeldataengineui.util.rule.action.IgnoreAction;
import com.adara.pixeldataengineui.util.rule.condition.ConditionChecker;
import com.adara.pixeldataengineui.util.rule.parse.Parser;

public class PixelDataEngineConfig {
    private String gid;
    private String keyId;
    private String priority;
    private String type;
    private Parser parser;
    private ConditionChecker conditionChecker;
    private Action action;
    private String pythonCode;

    /**
     * @param gid
     * @param keyId
     * @param priority
     * @param type
     * @param parser
     * @param conditionChecker
     * @param action
     * @param pythonCode
     */
    public PixelDataEngineConfig(String gid, String keyId, String priority, String type, Parser parser,
                                 ConditionChecker conditionChecker, Action action,
                                 String pythonCode) {
        this.gid = gid;
        this.keyId = keyId;
        this.priority = priority;
        this.type = type;
        this.parser = parser;
        this.conditionChecker = conditionChecker;
        this.action = action;
        this.pythonCode = pythonCode;
    }

    public Boolean isIgnoreAction() {
        return action instanceof IgnoreAction;
    }

    /**
     * @return the gid
     */
    public String getGid() {
        return gid;
    }

    /**
     * @param gid the gid to set
     */
    public void setGid(String gid) {
        this.gid = gid;
    }

    /**
     * @return the keyId
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * @param keyId the keyId to set
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @return priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the parser
     */
    public Parser getParser() {
        return parser;
    }

    /**
     * @param parser the parser to set
     */
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    /**
     * @return the conditionChecker
     */
    public ConditionChecker getConditionChecker() {
        return conditionChecker;
    }

    /**
     * @param conditionChecker the conditionChecker to set
     */
    public void setConditionChecker(ConditionChecker conditionChecker) {
        this.conditionChecker = conditionChecker;
    }

    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * @return the pythonCode
     */
    public String getPythonCode() {
        return pythonCode;
    }

    /**
     * @param pythonCode the pythonCode to set
     */
    public void setPythonCode(String pythonCode) {
        this.pythonCode = pythonCode;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PixelDataEngineConfig [" + "gid:" + gid + ", keyId=" + keyId + ", priority=" + priority
                + ", type=" + type
                + ", parser=" + parser + ", conditionChecker="
                + conditionChecker + ", action=" + action + ", pythonCode="
                + pythonCode + "]";
    }

}