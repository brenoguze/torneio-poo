package game;

import combate.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    private final List<Combatente> timeA;
    private final List<Combatente> timeB;
    private final Random rnd = new Random();

    public Arena(List<Combatente> timeA, List<Combatente> timeB) {
        this.timeA = timeA;
        this.timeB = timeB;
    }

    public void iniciar() {
        String primeiro = rnd.nextBoolean() ? "A" : "B";

        System.out.println("\nSorteando quem começa...");
        System.out.println("Resultado: TIME " + primeiro + " começa!");

        int rodada = 1;

        while (temVivos(timeA) && temVivos(timeB)) {
            System.out.println("\n==================================");
            System.out.println("RODADA " + rodada);
            System.out.println("==================================");

            if (primeiro.equals("A")) {
                turno(timeA, timeB, "A");
                if (!temVivos(timeB)) break;

                turno(timeB, timeA, "B");
            } else {
                turno(timeB, timeA, "B");
                if (!temVivos(timeA)) break;

                turno(timeA, timeB, "A");
            }

            imprimirStatus();
            rodada++;
        }

        System.out.println("\n=== FIM DA BATALHA ===");
        System.out.println(temVivos(timeA) ? "VENCEDOR: Time A" : "VENCEDOR: Time B");
    }

    private void turno(List<Combatente> atacantes, List<Combatente> defensores, String nomeTime) {
        System.out.println("\n---- VEZ DO TIME " + nomeTime + " ----");

        for (Combatente atacante : atacantes) {
            if (!atacante.estaVivo()) continue;

            Combatente alvo = escolherAlvoAleatorio(defensores);
            if (alvo == null) return;

            int danoBruto = atacar(atacante);
            int danoFinal = alvo.defender(danoBruto, rnd);
            int defesa = danoBruto - danoFinal;

            int pvAntes = alvo.getPv();
            alvo.receberDano(danoFinal);

            String infoAtaque = "";
            if (atacante instanceof Arcanista a) {
                if (!a.getUltimoTipoAtaque().isEmpty()) {
                    infoAtaque = " (" + a.getUltimoTipoAtaque() + ") Mana: "
                            + a.getManaAntes() + " -> " + a.getManaDepois();
                }
            } else if (atacante instanceof Cacador c) {
                if (c.foiUltimoCritico()) {
                    infoAtaque = " (CRÍTICO)";
                }
            }

            String infoDefesa = "";
            if (alvo instanceof Guardiao g) {
                if (!g.getUltimoEvento().isEmpty()) {
                    infoDefesa = " (" + g.getUltimoEvento() + ")";
                    if ("BLOQUEIO".equals(g.getUltimoEvento())) {
                        infoDefesa += " Vigor: " + g.getVigorAntes() + " -> " + g.getVigorDepois();
                    } else {
                        infoDefesa += " Vigor: " + g.getVigor();
                    }
                } else {
                    infoDefesa = " Vigor: " + g.getVigor();
                }
            }

            System.out.println(
                    atacante.getNome() + infoAtaque +
                            " atacou " + alvo.getNome() +
                            " | Dano bruto: " + danoBruto +
                            " | Defesa: " + defesa + infoDefesa +
                            " | Dano final: " + danoFinal +
                            " | PV " + alvo.getNome() + ": " + pvAntes + " -> " + alvo.getPv()
            );

            if (pvAntes > 0 && !alvo.estaVivo()) {
                System.out.println(">>> " + alvo.getNome() + " foi derrotado(a)!");
            }
        }
    }

    private int atacar(Combatente c) {
        if (c instanceof Guardiao g) return g.atacar(rnd);
        if (c instanceof Arcanista a) return a.atacar(rnd);
        if (c instanceof Cacador ca) return ca.atacar(rnd);
        return 0;
    }

    private Combatente escolherAlvoAleatorio(List<Combatente> time) {
        List<Combatente> vivos = new ArrayList<>();
        for (Combatente c : time) {
            if (c.estaVivo()) vivos.add(c);
        }
        if (vivos.isEmpty()) return null;
        return vivos.get(rnd.nextInt(vivos.size()));
    }

    private boolean temVivos(List<Combatente> time) {
        for (Combatente c : time) {
            if (c.estaVivo()) return true;
        }
        return false;
    }

    private void imprimirStatus() {
        System.out.println("\n-- STATUS TIME A --");
        for (Combatente c : timeA) {
            System.out.println(formatarStatus(c));
        }

        System.out.println("\n-- STATUS TIME B --");
        for (Combatente c : timeB) {
            System.out.println(formatarStatus(c));
        }
        System.out.println();
    }

    private String formatarStatus(Combatente c) {
        String base = c.getNome() + " | PV: " + c.getPv();

        if (c instanceof Guardiao g) {
            base += " | Vigor: " + g.getVigor();
        } else if (c instanceof Arcanista a) {
            base += " | Mana: " + a.getMana() + "/" + a.getManaMax();
        }

        if (!c.estaVivo()) {
            base += " (DERROTADO)";
        }

        return base;
    }
}
