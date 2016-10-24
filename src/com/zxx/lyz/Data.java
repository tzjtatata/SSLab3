package com.zxx.lyz;

/**
 * Created by 李沅泽 on 2016/10/23.
 */
public class Data {
    private double[] attr;
    private int label;

    public Data(int dimension) {
        attr = new double[dimension];
    }

    public double[] getAttr() {
        return attr;
    }

    public void setAttr(double[] attr) {
        this.attr = attr;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
}
