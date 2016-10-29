package com.zxx.lyz;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ReadInData readSet = new ReadInData("G:/KDD.dat");
        ArrayList<Data> dataBase = readSet.readTrainingData(50000);
        Formal formalUnit = new Formal(dataBase);
        ArrayList<Data> formalizedDatabase = formalUnit.getResult();
        KDTree kdTree = new KDTree(41, formalizedDatabase);
        ArrayList<Data> testingDataBase = formalUnit.formalWithOldParams(readSet.readTestingData(50000));
        Conner connerUnit = new Conner(testingDataBase, kdTree, 2.37);
        double accuracy = connerUnit.test();
        System.out.println(accuracy);
    }
}
