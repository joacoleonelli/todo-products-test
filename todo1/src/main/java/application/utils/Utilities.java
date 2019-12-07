package application.utils;

import java.util.Random;


public final class Utilities {
    /**
     * Genera un id aleatorio.
     * @return El id.
     */
    public static int getRandomId() {
        Random r = new Random();
        return r.nextInt((10000 - 1) + 1) + 1;
    }
}
