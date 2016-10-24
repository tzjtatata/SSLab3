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
    private final int MAXVOLUMNOFSAMPLES = 100;
    private final int MAXVOLUMN = 10;
    private final double SAMPLINGRATES = 0.001;
    private int volumn;
    private double midPointOfDevidedDimension;
    private int DevidedDimensionOrder;
    private final int DIMENSION;
    public Node leftChild;
    public Node rightChild;
    public boolean leafOrNot = false;

    public double getMidPointOfDevidedDimension() {
        return midPointOfDevidedDimension;
    }

    public ArrayList<Data> getPoints() {
        return points;
    }

    public void setMidPointOfDevidedDimension(double midPointOfDevidedDimension) {
        this.midPointOfDevidedDimension = midPointOfDevidedDimension;
    }

    public int getDevidedDimensionOrder() {
        return DevidedDimensionOrder;
    }

    public void setDevidedDimensionOrder(int devidedDimensionOrder) {
        DevidedDimensionOrder = devidedDimensionOrder;
    }
    public Node(ArrayList<Data> dataSlide, int dimension) {
        points = dataSlide;
        volumn = dataSlide.size();
        DIMENSION = dimension;
        if (LargeOrNot()) {
            findDevideDimension();
        }
    }
    /*
        This function is build to find the dimension
        which are most extensional. And Devided will
        apply in this dimension in this point of
        KD-Tree.

        @return int type, presents the order of
        the dimension.

        @param
     */
    public int findDevideDimension() {
        if (volumn * SAMPLINGRATES > MAXVOLUMNOFSAMPLES) {
            return findDevideDimensionWithSampling();
        }
        else {
            return findDevideDimensionWithStatics();
        }
    }

    public int findDevideDimensionWithStatics() {
        return findDevideDimensionByGap(points);
    }

    public int findDevideDimensionWithSampling() {
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
        return findDevideDimensionByVariance(samples);
    }

    public int findDevideDimensionByGap(ArrayList<Data> samples) {
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
        midPointOfDevidedDimension = UsedMath.getMinMedianAndMaxAtIthDimension(points, record)[1];
        DevidedDimensionOrder = record;
        return record;
    }

    public int findDevideDimensionByVariance(ArrayList<Data> samples) {
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
        midPointOfDevidedDimension = UsedMath.getMinMedianAndMaxAtIthDimension(points, dimensionOrder)[1];
        DevidedDimensionOrder = dimensionOrder;
        return dimensionOrder;

    }

    public boolean LargeOrNot() {
        return volumn > MAXVOLUMN;
    }

    public void devide() {
        ArrayList<Data> leftData = new ArrayList<Data>();
        ArrayList<Data> rightData = new ArrayList<Data>();
        for (Data point : points) {
            if (point.getAttr()[DevidedDimensionOrder] < midPointOfDevidedDimension) {
                leftData.add(point);
            } else rightData.add(point);
        }
        leftChild = new Node(leftData, DIMENSION);
        rightChild = new Node(rightData, DIMENSION);
        points = null;   // I try to save some memory, but I am not sure that this methods can work.
    }
}
