package br.ucsal;

public class Match extends Thread {
    private final String WHITE_PLAYER = "White";
    private final String BLACK_PLAYER = "Black";
    private long time = 600;
    private Clock whiteClock = new Clock(time, WHITE_PLAYER);
    private Clock blackClock = new Clock(time, BLACK_PLAYER);
    private MatchTimer matchTimer = new MatchTimer();

    @Override
    public void run() {

        System.out.println("Begin!");
        while (whiteClock.hasTimeLeft() && blackClock.hasTimeLeft()) {

            System.out.println("Black -> " + blackClock.getTimeLeft());
            System.out.println("White -> " + whiteClock.getTimeLeft());
        }
        stopClocks();
        determineWinner();
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
        } catch (Exception e){
            System.err.println(e);
        }
    }

    public void blackPlayed(){
        try{
            if(!whiteClock.isAlive()) whiteClock.start();
            blackClock.wait();
            whiteClock.notify();
        } catch (Exception e){}
    }


    private void stopClocks() {
        whiteClock.interrupt();
        blackClock.interrupt();
    }

    private void determineWinner() {
        if (whiteClock.isExpired()) {
            System.out.println("Black wins on time");
        } else if (blackClock.isExpired()) {
            System.out.println("White wins on time");
        }
    }

    public String getWhiteClockTime(){
        return Utils.formatTime(whiteClock.getTimeLeft());
    }
    public String getBlackClockTime(){
        return Utils.formatTime(blackClock.getTimeLeft());
    }
}