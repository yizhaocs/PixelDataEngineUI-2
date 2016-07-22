package com.adara.pixeldataengineui.model.frontend.generalcomponents;

/**
 * Created by yzhao on 5/26/16.
 */
public class Contains {
    private String seg;
    private String row;
    private String column;
    private String charString;

    public String getSeg() {
        return seg;
    }

    public void setSeg(String seg) {
        this.seg = seg;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getCharString() {
        return charString;
    }

    public void setCharString(String charString) {
        this.charString = charString;
    }

    @Override
    public String toString() {
        return "seg:" + seg.toString() + ", row:" + row + ", column:" + column + ", charString:" + charString;
    }
}
