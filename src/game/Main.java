package game;

import combate.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Combatente> timeA = new ArrayList<>();
        timeA.add(new Guardiao("Ana", 2));
        timeA.add(new Arcanista("Breno", 2));

        List<Combatente> timeB = new ArrayList<>();
        timeB.add(new Cacador("Nox", 1));
        timeB.add(new Guardiao("Mira", 1));

        Arena arena = new Arena(timeA, timeB);
        arena.iniciar();
    }
}