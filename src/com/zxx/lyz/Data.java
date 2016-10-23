package com.zxx.lyz;

/**
 * Created by 李沅泽 on 2016/10/23.
 */
public class Data {
    private int[] attr;
    private int label;

    public Data(int dimension) {
        attr = new int[dimension];
    }

    public int[] getAttr() {
        return attr;
    }

    public void setAttr(int[] attr) {
        this.attr = attr;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
}
