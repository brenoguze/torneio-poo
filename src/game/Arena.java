package game;

import combate.*;

import java.util.List;
import java.util.Random;

public class Arena {

    private List<Combatente> timeA;
    private List<Combatente> timeB;
    private Random rnd = new Random();

    public Arena(List<Combatente> timeA, List<Combatente> timeB) {
        this.timeA = timeA;
        this.timeB = timeB;
    }

    public void iniciar() {
        int rodada = 1;

        while (temVivos(timeA) && temVivos(timeB)) {
            System.out.println("\n=== RODADA " + rodada + " ===");

            turno(timeA, timeB, "A");
            if (!temVivos(timeB)) break;

            turno(timeB, timeA, "B");
            rodada++;
        }

        System.out.println("\n=== FIM DA BATALHA ===");
        if (temVivos(timeA)) {
            System.out.println("VENCEDOR: Time A");
        } else {
            System.out.println("VENCEDOR: Time B");
        }
    }

    private void turno(List<Combatente> atacantes, List<Combatente> defensores, String nomeTime) {
        System.out.println("Vez do Time " + nomeTime);

        for (Combatente atacante : atacantes) {
            if (!atacante.estaVivo()) continue;

            Combatente alvo = escolherAlvo(defensores);
            if (alvo == null) return;

            int dano = atacar(atacante);
            alvo.receberDano(dano);

            System.out.println(
                atacante.getNome() + " atacou " +
                alvo.getNome() + " causando " + dano +
                " (PV alvo: " + alvo.getPv() + ")"
            );
        }
    }

    private int atacar(Combatente c) {
        if (c instanceof Guardiao g) {
            return g.atacar(rnd);
        }
        if (c instanceof Arcanista a) {
            return a.atacar(rnd);
        }
        if (c instanceof Cacador ca) {
            return ca.atacar(rnd);
        }
        return 0;
    }

    private Combatente escolherAlvo(List<Combatente> time) {
        return time.stream().filter(Combatente::estaVivo).findAny().orElse(null);
    }

    private boolean temVivos(List<Combatente> time) {
        return time.stream().anyMatch(Combatente::estaVivo);
    }
}