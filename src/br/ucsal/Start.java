package br.ucsal;

import br.ucsal.swing.Swing;

public class Start {
    public static void main(String[] args) {
        Match match = new Match();
        new Swing(match);
        match.start();
    }
}
