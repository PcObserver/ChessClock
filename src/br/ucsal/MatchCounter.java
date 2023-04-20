package br.ucsal;

public class MatchCounter extends Thread {
    private long matchTime = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                matchTime++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getMatchTime() {
        return matchTime;
    }
}
