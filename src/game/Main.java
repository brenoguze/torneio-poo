package game;

import combate.*;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();

        Combatente a = new Guardiao("Ana", 2);
        Combatente b = new Arcanista("Breno", 2);
        Combatente c = new Cacador("Nox", 1);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        System.out.println("Exemplo ataque Guardião: " + ((Guardiao)a).atacar(rnd));
        System.out.println("Exemplo ataque Arcanista: " + ((Arcanista)b).atacar(rnd));
        System.out.println("Exemplo ataque Caçador: " + ((Cacador)c).atacar(rnd));
    }
}
