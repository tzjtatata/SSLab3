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
    private double midPointOfDividedDimension;
    private double dividedDimensionOrder;
    private final int DIMENSION;

    public Node(ArrayList<Data> dataSlide, int dimension) {
        points = dataSlide;
        volumn = dataSlide.size();
        DIMENSION = dimension;
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
        return findDiviceDimensionByGap(points);
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
        return findDiviceDimensionByVariance(samples);
    }

    public int findDiviceDimensionByGap(ArrayList<Data> samples) {
        int sampleSize = samples.size();
        double[] midpoints = new double[DIMENSION];
        double[] max = new double[DIMENSION];
        double[] min = new double[DIMENSION];
        double maxMidPoint = 0;
        int record = 0;
        for (int i = 0; i < DIMENSION; i++) {
            max[i] = 0;
            min[i] = 1e20;
        }
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < sampleSize; j++) {
                double Temp = samples.get(j).getAttr()[i];
                if (Temp > max[i]) {
                    max[i] = Temp;
                    midpoints[i] = (max[i] - min[i]) / 2;
                }
                if (Temp < min[i]) {
                    min[i] = Temp;
                    midpoints[i] = (max[i] - min[i]) / 2;
                }
            }
            if (midpoints[i] > maxMidPoint) {
                maxMidPoint = midpoints[i];
                record = i;
            }
        }
        midPointOfDividedDimension = maxMidPoint;
        dividedDimensionOrder = record;
        return record;
    }

    public int findDiviceDimensionByVariance(ArrayList<Data> samples) {
        double maxVariance = 0.0;
        int dimensionOrder = 0;
        double max = 0;
        double min = 1e20;
        for (int i = 0; i < DIMENSION; i++) {
            double TempD = 0;
            ArrayList<Double> Temp = new ArrayList<Double>();
            for (int j = 0; j < samples.size(); j++) {
                Temp.add(samples.get(j).getAttr()[i]);
            }
            TempD = UsedMath.calVariances(Temp);
            if (maxVariance < TempD) {
                maxVariance = TempD;
                dimensionOrder = i;
            }
        }
        for (int i = 0; i < volumn; i++) {
            double Temp = samples.get(i).getAttr()[dimensionOrder];
            if (Temp > max) {
                max = Temp;
            }
            if (Temp < min) {
                min = Temp;
            }
        }
        midPointOfDividedDimension = (max - min) / 2;
        dividedDimensionOrder = dimensionOrder;
        return dimensionOrder;

    }
}
