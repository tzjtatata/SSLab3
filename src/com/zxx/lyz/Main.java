package com.zxx.lyz;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        HashSet dataBase = new HashSet();
        ReadInData readSet = new ReadInData(dataBase, "KDD.dat");
        KDTree kdTree = new KDTree(42);
        kdTree.init(dataBase);
    }
}
