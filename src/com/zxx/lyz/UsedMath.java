package com.zxx.lyz;

import java.util.ArrayList;
import java.util.Collections;

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

    public static double[] getMinMedianAndMaxAtIthDimension(ArrayList<Data> samples, int i) {
        ArrayList<Double> ithData = new ArrayList<>();
        for(Data sample : samples)
            ithData.add(sample.getAttr()[i]);
        Collections.sort(ithData);
        int size = ithData.size();
        double[] result = new double[3];
        if (size % 2 == 1)
            result[1] = ithData.get((size-1)/2);
        else {
            double sum = ithData.get(size/2);
            sum += ithData.get(size/2-1);
            result[1] =  sum/2;
        }
        result[0] = ithData.get(0);
        result[2] = ithData.get(size-1);
        return result;
    }
}
