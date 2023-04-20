package br.ucsal;

import br.ucsal.swing.Swing;

public class Match {
    private final String WHITE_PLAYER = "White";
    private final String BLACK_PLAYER = "Black";
    private long time = 600;
    private Clock whiteClock = new Clock(time, WHITE_PLAYER);
    private Clock blackClock = new Clock(time, BLACK_PLAYER);
    private MatchTimer matchTimer;

    public Match(){
        Swing gui = new Swing();
        gui.startUI();
        matchTimer = new MatchTimer(gui,blackClock,whiteClock);

        gui.getButton1().addActionListener(e -> whitePlayed());
        gui.getButton2().addActionListener(e -> blackPlayed());

    }


    public void whitePlayed() {
        System.out.println("white");
        System.out.println(blackClock.getState());
        try {

                if (!blackClock.isAlive()) {
                    matchTimer.start();
                    blackClock.start();
                } else {
                    synchronized (whiteClock) {whiteClock.wait();}
                    synchronized (blackClock)  {blackClock.notify();}
                }

        } catch (Exception ignored) {
            // Handle the exception appropriately
        }
    }

    public void blackPlayed() {
        System.out.println("black");
        System.out.println(whiteClock.getState());
        try {

                if (!whiteClock.isAlive()) {
                    whiteClock.start();
                    synchronized (blackClock) { blackClock.wait();}
                } else {
                    synchronized (blackClock) { blackClock.wait();}
                    synchronized (whiteClock) {  whiteClock.notify();}
                }

        } catch (Exception ignored) {
            // Handle the exception appropriately
        }
    }

    public String getWhiteClockTime(){
        return Utils.formatTime(whiteClock.getTimeLeft());
    }
    public String getBlackClockTime(){
        return Utils.formatTime(blackClock.getTimeLeft());
    }
}