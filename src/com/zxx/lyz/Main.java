package com.zxx.lyz;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ReadInData readSet = new ReadInData("KDD.dat");
        ArrayList<Data> dataBase = readSet.readTrainingData(1000);
        Formal formalUnit = new Formal(dataBase);
        ArrayList<Data> formalizedDatabase = formalUnit.getResult();
        KDTree kdTree = new KDTree(42, formalizedDatabase);


    }
}
