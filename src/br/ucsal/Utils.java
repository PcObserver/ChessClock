package br.ucsal;

public class Utils {

    public static String formatTime(long time) {
        long minutes = time / 60;
        long seconds = time % 60;

        return minutes + ":" + seconds;
    }
}
