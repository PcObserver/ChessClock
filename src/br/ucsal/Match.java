package br.ucsal;

public class Match extends Thread {
    private final String WHITE_PLAYER = "White";
    private final String BLACK_PLAYER = "Black";
    private long time = 600;
    private Clock whiteClock = new Clock(time, WHITE_PLAYER);
    private Clock blackClock = new Clock(time, BLACK_PLAYER);
    private MatchCounter matchCounter = new MatchCounter();

    @Override
    public void run() {
        whiteClock.start();
        System.out.println("Begin!");
        while (whiteClock.hasTimeLeft() && blackClock.hasTimeLeft()) {

            playTurn(whiteClock);
            playTurn(blackClock);
            System.out.println("Black -> " + blackClock.getTimeLeft());
            System.out.println("White -> " + whiteClock.getTimeLeft());
        }
        stopClocks();
        determineWinner();
    }

    private void playTurn(Clock clock) {

        System.out.println("Play -> " + clock.getPlayerName());
        try {
            if (clock.getPlayerName().equals(BLACK_PLAYER)) {
                whiteClock.wait();
                if(!clock.isAlive()) clock.start();
                else blackClock.notify();
            } else {
                blackClock.wait();
                whiteClock.notify();
            }
        }catch (Exception e){

         }
    }
    public synchronized void whitePlayed() {
        try{
            if (!blackClock.isAlive()) blackClock.start();
            else {
                whiteClock.wait();
                blackClock.notify();
            }
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

}