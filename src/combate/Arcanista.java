package combate;

import java.util.Random;

public class Arcanista extends Combatente {

    private int mana;
    private int manaMax;

    private static final int CUSTO_FEITICO = 15;
    private static final int RECUPERA_MANA = 8;

    private String ultimoTipoAtaque = "";
    private int manaAntes = 0;
    private int manaDepois = 0;

    public Arcanista(String nome, int nivel) {
        super(nome, nivel, 80 + (nivel * 6));
        this.manaMax = 60 + (nivel * 8);
        this.mana = manaMax;
    }

    public int atacar(Random rnd) {
        manaAntes = mana;
        manaDepois = mana;
        ultimoTipoAtaque = "";

        if (mana >= CUSTO_FEITICO) {
            mana -= CUSTO_FEITICO;
            manaDepois = mana;
            ultimoTipoAtaque = "FEITIÇO";

            int base = 18 + (nivel * 3);
            return base + rnd.nextInt(10);
        }

        ultimoTipoAtaque = "FÍSICO";
        int baseFraco = 5 + nivel;
        int dano = baseFraco + rnd.nextInt(4);

        mana = Math.min(manaMax, mana + RECUPERA_MANA);
        manaDepois = mana;

        return dano;
    }

    public String getUltimoTipoAtaque() {
        return ultimoTipoAtaque;
    }

    public int getManaAntes() {
        return manaAntes;
    }

    public int getManaDepois() {
        return manaDepois;
    }

    public int getMana() {
        return mana;
    }

    public int getManaMax() {
        return manaMax;
    }

    @Override
    public String toString() {
        return nome + " (Arcanista Nv " + nivel + ", PV " + pv + ", Mana " + mana + "/" + manaMax + ")";
    }
}