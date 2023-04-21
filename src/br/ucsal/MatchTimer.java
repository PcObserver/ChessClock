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

    public void run() {

        long timer = System.currentTimeMillis();
        while (true) {
            render();

            try {
                Thread.sleep(1000);
                matchTime ++;

                if (!blackClock.hasTimeLeft()){
                    ui.updatePlayerOneTimer("BRANCAS GANHARAM");
                    ui.updatePlayerTwoTimer("BRANCAS GANHARAM");
                    ui.updateGameStateButton("Recomeçar!");
                    this.stop();
                }else if (!whiteClock.hasTimeLeft()){
                    ui.updatePlayerOneTimer("PRETAS GANHARAM");
                    ui.updatePlayerTwoTimer("PRETAS GANHARAM");
                    ui.updateGameStateButton("Recomeçar!");
                    this.stop();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void render() {
        ui.updatePlayerOneTimer(Utils.formatTime(whiteClock.getTimeLeft()));
        ui.updatePlayerTwoTimer(Utils.formatTime(blackClock.getTimeLeft()));
        ui.updateMatchTimer(Utils.formatTime(this.matchTime));
    }


    public long getMatchTime() {
        return matchTime;
    }
}
