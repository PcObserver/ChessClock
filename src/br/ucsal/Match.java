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
        matchTimer.start();
    }


    public void whitePlayed() {
        try{
            if (!blackClock.isAlive()) {
                matchTimer.start();
                blackClock.start();
            }
            else {
                whiteClock.wait();
                blackClock.notify();
            }
        } catch (Exception ignored){
        }
    }

    public void blackPlayed(){
        try{
            if(!whiteClock.isAlive()) whiteClock.start();
            blackClock.wait();
            whiteClock.notify();
        } catch (Exception ignored){
        }
    }


    public String getWhiteClockTime(){
        return Utils.formatTime(whiteClock.getTimeLeft());
    }
    public String getBlackClockTime(){
        return Utils.formatTime(blackClock.getTimeLeft());
    }
}