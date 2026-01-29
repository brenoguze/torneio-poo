package combate;

import java.util.Random;

public class Cacador extends Combatente {

    public Cacador(String nome, int nivel) {
        super(nome, nivel, 70 + (nivel * 5));
    }

    public int atacar(Random rnd) {
        int base = 10 + (nivel * 2);
        return base + rnd.nextInt(8);
    }

    @Override
    public String toString() {
        return nome + " (Caçador Nv " + nivel + ", PV " + pv + ")";
    }
}