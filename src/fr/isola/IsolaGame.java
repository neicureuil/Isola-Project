package fr.isola;

import fr.isola.ui.IsolaFrame;

/**
 * Classe principale permettant le lancement de l'application.
 */
class IsolaGame {

    /**
     * Fonction main de l'application.
     * @param args (unused)
     */
    public static void main(String[] args) {
        System.out.println("STARTING ISOLA ....");
        IsolaFrame frame = new IsolaFrame();
        frame.setVisible(true);
    }
    
}
