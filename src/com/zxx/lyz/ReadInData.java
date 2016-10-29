package com.zxx.lyz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by 李沅泽 on 2016/10/23.
 */
public class ReadInData {

    public ReadInData(String fileRoot) {
        fileName = fileRoot;
        lineChosen = new ArrayList<>();
    }

    private String fileName;
    private ArrayList<Integer> lineChosen;

    public ArrayList<Data> readTrainingData(int dataNumber) {
        ArrayList<Data> database = new ArrayList<>();
        File file = new File(fileName);
        BufferedReader bufferedReader;
        try {
            int count = 0;
            bufferedReader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            while(count < dataNumber && ((tempString = bufferedReader.readLine()) != null)) {
                Data tempData = getDataFromLine(tempString);
                line++;
                if (tempData.getLabel() == 1) {
                    count++;
                    database.add(tempData);
                    if (!lineChosen.contains(line))
                        lineChosen.add(line);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return database;
    }

    public ArrayList<Data> readTestingData(int dataNumber) {
        ArrayList<Data> database = new ArrayList<>();
        File file = new File(fileName);
        BufferedReader bufferedReader;
        try {
            int count = 0;
            bufferedReader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            while(count < dataNumber && ((tempString = bufferedReader.readLine()) != null)) {
                Data tempData = getDataFromLine(tempString);
                line++;
                if (!lineChosen.contains(line)) {
                    count++;
                    database.add(tempData);
                    lineChosen.add(line);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return database;
    }

    private Data getDataFromLine(String line) {
        String[] fragments = line.split(",");
        Data data = new Data(41);
        double[] attriArray = new double[41];
        for(int i = 0; i < 41; i++) {
            if (i == 0 || (i > 3 && i < 41))
                attriArray[i] = Double.parseDouble(fragments[i]);
        }
        for(int i = 1; i < 4; i++)
            attriArray[i] = hash(fragments[i]);
        data.setAttr(attriArray);
        if (fragments[41].equals("normal."))
            data.setLabel(1);
        else
            data.setLabel(0);
        return data;
    }
    int hash(String s) {
        int sum = 0;
        for(int i = 0; i < s.length(); i++) {
            sum *= 33;
            sum += s.charAt(i);
        }
        sum %= 71;
        return sum;
    }
}
