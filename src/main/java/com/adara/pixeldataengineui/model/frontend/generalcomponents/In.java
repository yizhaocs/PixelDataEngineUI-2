package com.adara.pixeldataengineui.model.frontend.generalcomponents;

/**
 * Created by yzhao on 7/22/16.
 */
public class In {
    private String seg;
    private String row;
    private String column;
    private InElementArray inElementArray;


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

    public InElementArray getInElementArray() {
        return inElementArray;
    }

    public void setInElementArray(InElementArray inElementArray) {
        this.inElementArray = inElementArray;
    }

    public class InElementArray {
        private String column1;
        private String column2;

        public String getColumn2() {
            return column2;
        }

        public void setColumn2(String column2) {
            this.column2 = column2;
        }

        public String getColumn1() {
            return column1;
        }

        public void setColumn1(String column1) {
            this.column1 = column1;
        }
    }
}
