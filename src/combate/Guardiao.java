package combate;

import java.util.Random;

public class Guardiao extends Combatente {

    public Guardiao(String nome, int nivel) {
        super(nome, nivel, 120 + (nivel * 10));
    }

    public int atacar(Random rnd) {
        int base = 8 + (nivel * 2);
        return base + rnd.nextInt(6);
    }

    @Override
    public String toString() {
        return nome + " (Guardião Nv " + nivel + ", PV " + pv + ")";
    }
}