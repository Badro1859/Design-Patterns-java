package org.emp.gl.core.launcher;


import org.emp.gl.core.lookup.Lookup;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {

    static {
        Lookup.getInstance().register(TimerService.class, new DummyTimeServiceImpl());
    }

    public static void main(String[] args) {
        //testAfficheurHeureSurConsole();

        // testCompteARebour();



    }

    public static void testAfficheurHeureSurConsole() {
        AfficheurHeureSurConsole displayer = new AfficheurHeureSurConsole();
    }

    public static void testCompteARebour() {
        Random rnd = new Random();
        for (int k=0; k<10; k++) {
            CompteARebour timer = new CompteARebour(rnd.nextInt(10) +5);
        }
    }


    private static void testDuTimeService() {

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
