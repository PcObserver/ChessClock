package br.ucsal;

import br.ucsal.swing.Swing;

public class Start {
    public static void main(String[] args) {
        Match match = new Match();
        Swing swing = new Swing();
        swing.updatePlayerOneTimer("100");
        swing.updatePlayerTwoTimer("100");
        match.start();
    }
}
