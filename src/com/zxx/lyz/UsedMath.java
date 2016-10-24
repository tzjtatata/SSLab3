package com.zxx.lyz;

import java.util.ArrayList;

/**
 * Created by 李沅泽 on 2016/10/24.
 */
public class UsedMath {
    public UsedMath() {

    }

    public static double calVariances(ArrayList<Double> data) {
        double ave = 0;
        double variance = 0;
        for (int i = 0; i < data.size(); i++) {
            ave += data.get(i);
        }
        ave /= data.size();
        for (int i = 0; i < data.size(); i++) {
            variance += sqr(ave - data.get(i));
        }
        return variance;
    }

    public static double sqr(double a) {
        return a * a;
    }
}
