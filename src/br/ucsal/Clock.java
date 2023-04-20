package br.ucsal;

public class Clock extends Thread  {

    private long timeLeft;
    private String playerName;
    private boolean expired = false;

    public Clock(long timeLimit, String playerName) {
        this.timeLeft = timeLimit;
        this.playerName = playerName;
    }


    @Override
    public void run() {

        while (this.hasTimeLeft()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeLeft -= 1;
            System.err.println(playerName + " -> " + timeLeft);
        }

    }
    public boolean hasTimeLeft() {
        return this.timeLeft > 0 ;
    }
    public long getTimeLeft() {
        return timeLeft;
    }

    public String getPlayerName() {
        return playerName;
    }


    public boolean isExpired() {
        return expired;
    }
}
