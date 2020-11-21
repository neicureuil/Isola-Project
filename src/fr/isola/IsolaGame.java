package fr.isola;

import fr.isola.deepl.Tester;
import fr.isola.ui.IsolaFrame;

class IsolaGame {

    public static void main(String[] args) {
        System.out.println("HELLO WORLD V4");

        IsolaFrame frame = new IsolaFrame();
        frame.setVisible(true);
        new Tester();
    }
    
}
