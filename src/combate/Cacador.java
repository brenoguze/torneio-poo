package combate;

import java.util.Random;

public class Cacador extends Combatente {

    private static final double CHANCE_CRITICO = 0.25;

    private boolean ultimoCritico = false;

    public Cacador(String nome, int nivel) {
        super(nome, nivel, 70 + (nivel * 5));
    }

    public int atacar(Random rnd) {
        ultimoCritico = false;

        int base = 10 + (nivel * 2);
        int dano = base + rnd.nextInt(8);

        if (rnd.nextDouble() < CHANCE_CRITICO) {
            ultimoCritico = true;
            dano = dano * 2;
        }

        return dano;
    }

    public boolean foiUltimoCritico() {
        return ultimoCritico;
    }

    @Override
    public String toString() {
        return nome + " (Caçador Nv " + nivel + ", PV " + pv + ")";
    }
}
