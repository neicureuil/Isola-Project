package fr.isola;

import fr.isola.deepl.Dataset;
import fr.isola.deepl.IaTrainer;
import fr.isola.ui.IsolaFrame;

class IsolaGame {

    public static void main(String[] args) {
        System.out.println("HELLO WORLD V4");

        IsolaFrame frame = new IsolaFrame();
        frame.setVisible(true);
        (new IaTrainer()).TrainNeuralIaMovement(new Dataset("moves_dataset_8_6.txt"), 8, 6);
    }
    
}
