package fr.isola.deepl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Dataset {

    private String filename;
    private BufferedWriter fileWriter;

    public Dataset(String filename) {
        this.filename = filename;
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
