package com.zxx.lyz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Xiangxi on 2016/10/24.
 */
public class Node {
    /*
        This class are the node of KD-Tree.
        If A Node presents the leaf of the Tree,
        IT WILL CONSIST OF DATA POINT.

        @param MAXVOLUMN
        This attribute give a bound.
        Above it, we use SAMPLING to calculate the extension of a dimension.
        Below it, we summarize whole data points
        and give the accurate answer to devide.
     */
    private ArrayList<Data> points;
    private final int MAXVOLUMN = 100;
    private final double SAMPLINGRATES = 0.001;
    private int volumn;
    public Node(ArrayList<Data> dataSlide) {
        points = dataSlide;
        volumn = dataSlide.size();
    }
    /*
        This function is build to find the dimension
        which are most extensional. And Divided will
        apply in this dimension in this point of
        KD-Tree.

        @return int type, presents the order of
        the dimension.

        @param
     */
    public int findDiviceDimension() {
        if (volumn*SAMPLINGRATES > MAXVOLUMN) {
            return findDiviceDimensionWithSampling();
        }
        else {
            return findDiviceDimensionWithStatics();
        }
    }

    public int findDiviceDimensionWithStatics() {
        return findDiviceDimensionByData(points);
    }

    public int findDiviceDimensionWithSampling() {
        Random randomUnit = new Random(11403410520L);
        HashSet<Integer> orders = new HashSet<Integer>();
        ArrayList<Data> samples = new ArrayList<Data>();
        int sampleVolumn = (int)(volumn * SAMPLINGRATES);
        while (orders.size() < sampleVolumn) {
            int Temp = randomUnit.nextInt(volumn);
            orders.add(Temp);
        }
        for (Integer i : orders) {
            samples.add(points.get(i.intValue()));
        }
        return findDiviceDimensionByData(samples);
    }

    public int findDiviceDimensionByData(ArrayList<Data> orders) {

    }
}
