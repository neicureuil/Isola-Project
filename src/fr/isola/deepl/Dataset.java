package fr.isola.deepl;

import java.io.*;
import java.util.Vector;

public class Dataset {

    private String filename;
    private BufferedWriter fileWriter;

    public Dataset(String filename) {
        this.filename = filename;
    }

    public Vector<double[]> loadDatas() {
        Vector<double[]> datas = new Vector<double[]>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("./"+this.filename));
            String line = br.readLine();
            while (line != null && !line.equals("")) {
                String[] sArr = line.split(" ");
                double[] lineData = new double[sArr.length];
                for(int i=0; i<lineData.length; i++){
                    lineData[i] = Double.parseDouble(sArr[i]);
                }
                datas.add(lineData);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public void initWriter() {
        try {
            fileWriter = new BufferedWriter(new FileWriter("./"+this.filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWriter() {
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addData(double[] datas) {
        try {
            for(int i=0; i<datas.length; i++) fileWriter.write(Double.toString(datas[i]) + " ");
            fileWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
