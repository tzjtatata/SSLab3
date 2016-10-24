package com.zxx.lyz;

import java.util.ArrayList;

/**
 * Created by Xiangxi on 2016/10/24.
 */
public class Formal {

    public Formal(ArrayList<Data> dataBase) {
        this.dataBase = (ArrayList<Data>)dataBase.clone();
        attributeNumber = dataBase.get(0).getAttr().length;
        coefficientArray = new ArrayList<>(attributeNumber);
        biasArray = new ArrayList<>(attributeNumber);
        for(int i = 0; i < attributeNumber; i++) {
            formalizeIthDimension(i);
        }
    }

    private ArrayList<Data> dataBase;
    private int attributeNumber;
    public static final double newMin = 0.0d;
    public static final double newMax = 1.0d;
    private ArrayList<Double> coefficientArray;
    private ArrayList<Double> biasArray;


    private void formalizeIthDimension(int i) {
        double[] minMedianAndMax = UsedMath.getMinMedianAndMaxAtIthDimension(dataBase, i);
        double min = minMedianAndMax[0];
        double max = minMedianAndMax[2];
        double coefficient = (newMax - newMin) / (max - min);
        double bias = newMin - min * coefficient;
        coefficientArray.set(i, coefficient);
        biasArray.set(i, bias);
        for (int k = 0; k < dataBase.size(); k++)
            dataBase.get(k).getAttr()[i] = dataBase.get(k).getAttr()[i] * coefficient + bias;
    }
    public ArrayList<Data> getResult() {
        return dataBase;
    }
    public ArrayList<Data> formalWithOldParams(ArrayList<Data> dataBase) {
        ArrayList<Data> formalizedResult = new ArrayList<>();
        for(Data data: dataBase)
            formalizedResult.add(formalizeData(data));
        return formalizedResult;
    }
    public Data formalizeData(Data data) {
        Data newData = new Data(data);
        for(int i = 0; i < attributeNumber; i++)
            newData.getAttr()[i] = data.getAttr()[i] * coefficientArray.get(i) + biasArray.get(i);
        return newData;
    }
}
