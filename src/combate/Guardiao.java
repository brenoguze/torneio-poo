package combate;

import java.util.Random;

public class Guardiao extends Combatente {

    private int vigor;

    private static final double CHANCE_BLOQUEIO = 0.30;
    private static final int CUSTO_BLOQUEIO = 10;

    private String ultimoEvento = "";
    private int vigorAntes;
    private int vigorDepois;

    public Guardiao(String nome, int nivel) {
        super(nome, nivel, 120 + (nivel * 10));
        this.vigor = 20 + (nivel * 5);
    }

    public int atacar(Random rnd) {
        int base = 8 + (nivel * 2);
        return base + rnd.nextInt(6);
    }

    @Override
    public int defender(int danoBruto, Random rnd) {
        ultimoEvento = "";
        vigorAntes = vigor;
        vigorDepois = vigor;

        boolean tentaBloquear = rnd.nextDouble() < CHANCE_BLOQUEIO;

        if (tentaBloquear && vigor >= CUSTO_BLOQUEIO) {
            vigor -= CUSTO_BLOQUEIO;
            vigorDepois = vigor;
            ultimoEvento = "BLOQUEIO";
            return 0;
        }

        if (tentaBloquear && vigor < CUSTO_BLOQUEIO) {
            ultimoEvento = "SEM VIGOR";
        }

        return danoBruto;
    }

    public String getUltimoEvento() {
        return ultimoEvento;
    }

    public int getVigorAntes() {
        return vigorAntes;
    }

    public int getVigorDepois() {
        return vigorDepois;
    }

    public int getVigor() {
        return vigor;
    }

    @Override
    public String toString() {
        return nome + " (Guardião Nv " + nivel + ", PV " + pv + ", Vigor " + vigor + ")";
    }
}