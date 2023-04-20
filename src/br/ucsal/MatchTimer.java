package br.ucsal;

import br.ucsal.swing.Swing;

public class MatchTimer extends Thread {
    private long matchTime = 0;

    private Clock blackClock;
    private Clock whiteClock;
    private Swing ui;

    public MatchTimer(Swing ui, Clock blackClock, Clock whiteClock){
        this.ui = ui;
        this.blackClock = blackClock;
        this.whiteClock = whiteClock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                matchTime++;
                ui.updatePlayerOneTimer(Utils.formatTime(whiteClock.getTimeLeft()));
                ui.updatePlayerTwoTimer(Utils.formatTime(blackClock.getTimeLeft()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getMatchTime() {
        return matchTime;
    }
}
