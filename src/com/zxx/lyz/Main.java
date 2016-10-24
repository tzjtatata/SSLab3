package com.zxx.lyz;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Data> dataBase = new ArrayList<>();
        ReadInData readSet = new ReadInData(dataBase, "KDD.dat");
        Formal formalUnit = new Formal(dataBase);
        ArrayList<Data> formalizedDatabase = formalUnit.getResult();
        KDTree kdTree = new KDTree(42, formalizedDatabase);


    }
}
