package com.zxx.lyz;

import java.util.ArrayList;

/**
 * Created by 李沅泽 on 2016/10/24.
 */
public class Conner {
    private ArrayList<Data> testSet;
    private KDTree testTree;
    private double Boundary;

    public Conner(ArrayList<Data> set, KDTree tree, double limit) {
        testSet = set;
        testTree = tree;
        Boundary = limit;
    }

    public double test() {
        int count = 0;
        double accuracy = 0;
        int volumn = testSet.size();
        for (int i = 0; i < volumn; i++) {
            double basis = calculateBasis(i);
            if (basis < Boundary) {
                if (testSet.get(i).getLabel() == 1) count += 1;
            }
            else {
                if (testSet.get(i).getLabel() == 0) count += 1;
            }
        }
        accuracy = count*1.0 / volumn;
        return accuracy;
    }

    private double calculateBasis(int order) {
        ArrayList<Data> nearestNeighbour = testTree.search(testSet.get(order)).getPoints();
        int volumn = nearestNeighbour.size();
        double basis = 0;
        for (int i = 0; i < volumn; i++) {
            basis += UsedMath.calEuclideanMetric(nearestNeighbour.get(i), testSet.get(order));
        }
        return basis / volumn;
    }
}
