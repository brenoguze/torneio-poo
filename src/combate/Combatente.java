package combate;

public abstract class Combatente {

    protected String nome;
    protected int nivel;
    protected int pv;

    public Combatente(String nome, int nivel, int pv) {
        this.nome = nome;
        this.nivel = nivel;
        this.pv = pv;
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPv() {
        return pv;
    }

    public boolean estaVivo() {
        return pv > 0;
    }

    public void receberDano(int dano) {
        pv = Math.max(0, pv - dano);
    }
}