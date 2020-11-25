package fr.isola.deepl;

import java.io.*;
import java.util.Vector;

/**
 * Classe Dataset representant un ensemble de données pour le DeepLearning.
 * Traite uniquement des fichiers contenant des listes de double.
 */
public class Dataset {

    /**
     * String contenant le nom du fichier de la dataset.
     */
    private String filename;
    /**
     * BufferedWriter d'ecriture dans le fichier de la dataset.
     */
    private BufferedWriter fileWriter;

    /**
     * Constructeur de la classe.
     * @param filename Nom du fichier contenant la dataset.
     */
    public Dataset(String filename) {
        this.filename = filename;
    }

    /**
     * Charge une dataset et en extrait les données.
     * @return Vecteur contant la liste des données de la dataset (une entree par ligne de la dataset).
     */
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

    /**
     * Initialise le writer pour l'ecriture dans le fichier de la dataset.
     */
    public void initWriter() {
        try {
            fileWriter = new BufferedWriter(new FileWriter("./"+this.filename, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ferme le writer pour de l'ecriture dans le fichier de la dataset.
     */
    public void closeWriter() {
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoute une ligne dans le fichier de la dataset.
     * Le writer doit être initialisé avant.
     * @param datas double[] array de double correspondant a une ligne de données.
     */
    public void addData(double[] datas) {
        try {
            for(int i=0; i<datas.length; i++) fileWriter.write(Double.toString(datas[i]) + " ");
            fileWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
