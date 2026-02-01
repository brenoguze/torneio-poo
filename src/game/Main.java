package game;

import combate.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== TORNEIO POO ===");
        System.out.println("Classes: 1-Guardião | 2-Arcanista | 3-Caçador");
        System.out.println("Nível: 1 a 3\n");

        List<Combatente> timeA = montarTime(sc, "A");
        List<Combatente> timeB = montarTime(sc, "B");

        Arena arena = new Arena(timeA, timeB);
        arena.iniciar();

        sc.close();
    }

    private static List<Combatente> montarTime(Scanner sc, String nomeTime) {
        System.out.print("Quantos guerreiros no Time " + nomeTime + "? ");
        int qtd = lerInteiro(sc, 1, 50);

        List<Combatente> time = new ArrayList<>();
        System.out.println();

        for (int i = 1; i <= qtd; i++) {
            System.out.println("Combatente " + i + " do Time " + nomeTime + ":");

            System.out.print("Nome: ");
            String nome = sc.nextLine().trim();
            while (nome.isEmpty()) {
                System.out.print("Nome não pode ser vazio. Digite novamente: ");
                nome = sc.nextLine().trim();
            }

            System.out.print("Classe (1-Guardião, 2-Arcanista, 3-Caçador): ");
            int classe = lerInteiro(sc, 1, 3);

            System.out.print("Nível (1 a 3): ");
            int nivel = lerInteiro(sc, 1, 3);

            Combatente c = criarCombatente(nome, classe, nivel);
            time.add(c);

            System.out.println("-> Criado: " + c);
            System.out.println();
        }

        return time;
    }

    private static Combatente criarCombatente(String nome, int classe, int nivel) {
        if (classe == 1) return new Guardiao(nome, nivel);
        if (classe == 2) return new Arcanista(nome, nivel);
        return new Cacador(nome, nivel);
    }

    private static int lerInteiro(Scanner sc, int min, int max) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v < min || v > max) {
                    System.out.print("Digite um número entre " + min + " e " + max + ": ");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número entre " + min + " e " + max + ": ");
            }
        }
    }
}
