package combate;

import java.util.Random;

public class Arcanista extends Combatente {

    public Arcanista(String nome, int nivel) {
        super(nome, nivel, 80 + (nivel * 6));
    }

    public int atacar(Random rnd) {
        int base = 18 + (nivel * 3);
        return base + rnd.nextInt(10);
    }

    @Override
    public String toString() {
        return nome + " (Arcanista Nv " + nivel + ", PV " + pv + ")";
    }
}