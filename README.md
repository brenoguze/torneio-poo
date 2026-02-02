# Torneio POO – Jogo de Batalha por Turnos

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos (POO), com o objetivo de aplicar, de forma prática, os principais conceitos do paradigma orientado a objetos por meio de um jogo simples de batalha por turnos.

---

## 📌 Descrição Geral

O projeto consiste em um jogo de batalha entre dois times (Time A e Time B), formados por personagens de diferentes classes. Cada personagem possui atributos e comportamentos específicos, e a batalha ocorre em rodadas até que todos os combatentes de um dos times sejam derrotados.

O foco do projeto não é a complexidade do jogo, mas sim a correta aplicação dos conceitos de Orientação a Objetos, seguindo as regras propostas no enunciado da disciplina.

---

## 🧙 Classes de Personagens

O jogo possui uma classe base chamada `Combatente`, da qual derivam três classes específicas:

### 🔰 Guardião de Ferro (Tanque)
- Possui maior resistência.
- Recurso especial: **Vigor**.
- Pode bloquear parcial ou totalmente ataques, consumindo vigor.
- A lógica de bloqueio está encapsulada na própria classe.

### 🔮 Arcanista (Mago)
- Personagem frágil, porém com alto dano.
- Recurso especial: **Mana**.
- Utiliza feitiços enquanto possui mana.
- Quando a mana acaba, realiza ataques físicos fracos que recuperam parte da mana.

### 🎯 Caçador (Atirador)
- Personagem ágil e com menor vida base.
- Possui chance de **acerto crítico**, que dobra o dano do ataque.
- Não utiliza recursos limitados.

---

## ⚙️ Funcionamento do Jogo

1. O usuário cria os dois times informando:
   - Quantidade de combatentes
   - Nome
   - Classe
   - Nível (1 a 3)

2. O sistema sorteia aleatoriamente qual time começa.

3. A batalha ocorre em rodadas:
   - Cada rodada possui a vez do Time A e do Time B.
   - Antes de cada turno, o usuário deve confirmar a ação digitando `1` para atacar.
   - Os combatentes vivos atacam automaticamente alvos vivos do time adversário.

4. A resolução de dano segue a ordem:
   - Cálculo do dano bruto do atacante
   - Tentativa de defesa do defensor
   - Aplicação do dano final aos pontos de vida

5. Ao final de cada rodada, o status dos personagens é exibido:
   - Pontos de Vida (PV)
   - Mana (Arcanista)
   - Vigor (Guardião)

6. O jogo termina quando todos os combatentes de um dos times são derrotados.

---

## 🧠 Conceitos de Programação Orientada a Objetos Aplicados

- **Abstração**:  
  A classe `Combatente` representa o conceito geral de um personagem do jogo.

- **Herança**:  
  As classes `Guardiao`, `Arcanista` e `Cacador` herdam de `Combatente`.

- **Polimorfismo**:  
  A Arena trata todos os personagens como `Combatente`, e o comportamento real depende da classe concreta em tempo de execução.

- **Encapsulamento**:  
  As regras específicas de cada personagem (mana, vigor, crítico) estão isoladas dentro de suas respectivas classes.

---

## 📂 Estrutura do Projeto

src/
├── combate/
│ ├── Combatente.java
│ ├── Guardiao.java
│ ├── Arcanista.java
│ └── Cacador.java
│
└── game/
├── Arena.java
└── Main.java