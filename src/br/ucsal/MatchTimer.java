package br.ucsal;

import br.ucsal.swing.Swing;


public class MatchTimer extends Thread {
    private long matchTime = 0;

    private Clock blackClock;
    private Clock whiteClock;
    private Swing ui;

    String timeLeftBlack;

    String timeLeftWhite;

    public MatchTimer(Swing ui, Clock blackClock, Clock whiteClock){
        this.ui = ui;
        this.blackClock = blackClock;
        this.whiteClock = whiteClock;
    }

    public void run() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                // Update game state
                updates++;
                delta--;
            }
            // Render the game
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
               // System.out.println("FPS: " + frames + " Ticks: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void render() {
        ui.updatePlayerOneTimer(String.valueOf(whiteClock.getTimeLeft()));
        ui.updatePlayerTwoTimer(String.valueOf(blackClock.getTimeLeft()));

    }

    public long getMatchTime() {
        return matchTime;
    }
}
