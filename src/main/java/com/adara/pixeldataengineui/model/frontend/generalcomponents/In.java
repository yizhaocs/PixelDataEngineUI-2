package com.adara.pixeldataengineui.model.frontend.generalcomponents;

import java.util.List;

/**
 * Created by yzhao on 7/22/16.
 */
public class In {
    private String seg;
    private String row;
    private String column;
    private List<InElementArray> inElementArray;


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

    public List<InElementArray> getInElementArray() {
        return inElementArray;
    }

    public void setInElementArray(List<InElementArray> inElementArray) {
        this.inElementArray = inElementArray;
    }
}
