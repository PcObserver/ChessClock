package br.ucsal;

import br.ucsal.swing.Swing;

public class Match {
    private static final long MATCH_DURATION = 300;
    private final String WHITE_PLAYER = "White";
    private final String BLACK_PLAYER = "Black";
    private Clock whiteClock = new Clock(MATCH_DURATION, WHITE_PLAYER);
    private Clock blackClock = new Clock(MATCH_DURATION, BLACK_PLAYER);
    private MatchTimer matchTimer;

    private Clock lastRunning = whiteClock;

    public Match() {
        Swing gui = new Swing();
        gui.startUI();
        matchTimer = new MatchTimer(gui, blackClock, whiteClock);

        gui.getPlayer1Button().addActionListener(e -> whitePlayed());
        gui.getPlayer2Button().addActionListener(e -> blackPlayed());
        gui.getGameStateButton().addActionListener(e -> {
            if (gui.getGameStateButton().getText().equals("Começar!")) {
                gameStart();
            }else if (gui.getGameStateButton().getText().equals("Recomeçar!")) {
                gameReset(gui);
                gameStart();
            } else {
                matchTimer.suspend();
                lastRunning.suspend();
            }
        });
        gui.getResumeButton().addActionListener(e -> {
            matchTimer.resume();
            lastRunning.resume();
        });
        gui.getResetButton().addActionListener(e -> {
            gameReset(gui);
        });
    };

    public void gameReset(Swing gui) {
        whiteClock = new Clock(MATCH_DURATION, WHITE_PLAYER);
        blackClock = new Clock(MATCH_DURATION, BLACK_PLAYER);
        matchTimer = new MatchTimer(gui, blackClock, whiteClock);
        gui.resetFields();
    }
    public void whitePlayed() {
        try {

            if (!blackClock.isAlive()) {
                whiteClock.suspend();
                blackClock.start();
            } else {
                whiteClock.suspend();
                blackClock.resume();
            }

            lastRunning = blackClock;
        } catch (Exception ignored) {
        }
    }

    public void blackPlayed() {
        try {

            if (!whiteClock.isAlive()) {

                 blackClock.suspend();
            } else {
                 blackClock.suspend();
                 whiteClock.resume();
            }

            lastRunning = whiteClock;
        } catch (Exception ignored) {}
    }

    public void gameStart(){
        matchTimer.start();
        whiteClock.start();
    }
}