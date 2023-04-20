package br.ucsal.swing;

import br.ucsal.Clock;
import br.ucsal.Match;

import javax.swing.*;
import java.awt.*;

public class Swing extends JFrame {

    private JLabel player1, player2, player1timer, player2timer;

    private JButton button1, button2;



    public Swing() {
    }

    public void updatePlayerOneTimer(String time) {
        player1timer.setText(time);
    }
    public void updatePlayerTwoTimer(String time) {
        player2timer.setText(time);
    }

    private void setupUI() {
        setTitle("XADRAS DA GALERA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
    }
    public void startUI() {
        setupUI();
        createElements();

        // create panel to hold components
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(player1);
        panel.add(player2);
        panel.add(player1timer);
        panel.add(player2timer);
        // create panel to hold buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        button2.setEnabled(false);


        // create buttons logic
        button1.addActionListener(e -> {
            button1.setEnabled(false);
            button2.setEnabled(true);
        });
        button2.addActionListener(e -> {
            button2.setEnabled(false);
            button1.setEnabled(true);
        });



        // add panels to main frame
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void createElements() {
        // create text fields
        player1 = new JLabel("Jogador Branco");
        player1.setHorizontalAlignment(JLabel.CENTER);
        player1timer = new JLabel();
        player1timer.setHorizontalAlignment(JLabel.CENTER);
        player2 = new JLabel("Jogador Preto");
        player2.setHorizontalAlignment(JLabel.CENTER);
        player2timer = new JLabel();
        player2timer.setHorizontalAlignment(JLabel.CENTER);
        // create buttons
        button1 = new JButton("Terminar minha jogada!");
        button2 = new JButton("Terminar minha jogada!");
    }
    public JButton getButton1() {
        return button1;
    }

    public void setButton1(JButton button1) {
        this.button1 = button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public void setButton2(JButton button2) {
        this.button2 = button2;
    }

}