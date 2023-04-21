package br.ucsal.swing;

import javax.swing.*;
import java.awt.*;

public class Swing extends JFrame {

    private JLabel player1, player2, player1timer, player2timer, matchTimer;;

    private JButton player1Button, player2Button, resumeButton, resetButton, gameStateButton;

    private JPanel gameScreen = new JPanel(new GridLayout(2, 3));
    private JPanel pauseScreen = new JPanel(new FlowLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    public Swing() {
    }

    private void setupUI() {
        setTitle("XADRAS DA GALERA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);
    }

    public void pauseScreen() {
        pauseScreen.add(resumeButton);
        pauseScreen.setVisible(false);
        pauseScreen.add(resetButton);

        resumeButton.addActionListener(e -> {
            pauseScreen.setVisible(false);
            gameScreen.setVisible(true);
            buttonPanel.setVisible(true);
        });

        resetButton.addActionListener(e -> {
            pauseScreen.setVisible(false);
            gameScreen.setVisible(true);
            buttonPanel.setVisible(true);
        });

        getContentPane().add(pauseScreen, BorderLayout.NORTH);
    }

    public void gameScreen() {
        // create panel to hold components
        gameScreen.add(player1);
        gameScreen.add(matchTimer);
        gameScreen.add(player2);
        gameScreen.add(player1timer);
        gameScreen.add(gameStateButton);
        gameScreen.add(player2timer);
        // create gameScreen to hold buttons

        buttonPanel.add(player1Button);
        buttonPanel.add(player2Button);
        player2Button.setEnabled(false);


        // create buttons logic
        player1Button.addActionListener(e -> {
            player1Button.setText("Terminar minha jogada!");
            player1Button.setEnabled(false);
            player2Button.setEnabled(true);
        });
        player2Button.addActionListener(e -> {
            player2Button.setEnabled(false);
            player1Button.setEnabled(true);
        });

        gameStateButton.addActionListener(e -> {
            if(gameStateButton.getText().equals("Pausar!")) {
                pauseScreen.setVisible(true);
                gameScreen.setVisible(false);
                buttonPanel.setVisible(false);
            } else {
                gameStateButton.setText("Pausar!");
            }
        });

        getContentPane().add(gameScreen, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    public void startUI() {
        setupUI();
        createElements();

        pauseScreen();
        gameScreen();

        setVisible(true);
    }

    private void createElements() {
        // create text fields
        player1 = new JLabel("Peças Brancas");
        player1.setHorizontalAlignment(JLabel.CENTER);
        player1timer = new JLabel();
        player1timer.setHorizontalAlignment(JLabel.CENTER);
        player2 = new JLabel("Peças Pretas");
        player2.setHorizontalAlignment(JLabel.CENTER);
        player2timer = new JLabel();
        player2timer.setHorizontalAlignment(JLabel.CENTER);
        matchTimer = new JLabel("Tempo Total");
        matchTimer.setHorizontalAlignment(JLabel.CENTER);
        // create buttons
        player1Button = new JButton("Terminar minha jogada!");
        player2Button = new JButton("Terminar minha jogada!");
        resumeButton = new JButton("Continuar!");
        resetButton = new JButton("Reiniciar");
        gameStateButton = new JButton("Começar!");
    }

    public void resetFields() {
        player1timer.setText("");
        player2timer.setText("");
        matchTimer.setText("Tempo Total");
        gameStateButton.setText("Começar!");
        player1Button.setEnabled(true);
        player2Button.setEnabled(false);
    }

    public void updateGameStateButton(String text) {
        gameStateButton.setText(text);
    }
    public void updateMatchTimer(String time) {
        matchTimer.setText(time);
    }
    public void updatePlayerOneTimer(String time) {
        player1timer.setText(time);
    }
    public void updatePlayerTwoTimer(String time) {
        player2timer.setText(time);
    }
    public JButton getPlayer1Button() {
        return player1Button;
    }
    public JButton getPlayer2Button() {
        return player2Button;
    }

    public JButton getResumeButton() {
        return resumeButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getGameStateButton() {
        return gameStateButton;
    }


}