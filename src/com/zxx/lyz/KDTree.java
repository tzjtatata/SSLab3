package com.zxx.lyz;

import java.util.HashSet;

/**
 * Created by 李沅泽 on 2016/10/23.
 */
public class KDTree {

    private final int DIMENSION;
    private HashSet pointSet = new HashSet();

    public KDTree(int dimension) {
        DIMENSION = dimension;
    }

    public void init(HashSet data) {
        pointSet = data;
    }
}
