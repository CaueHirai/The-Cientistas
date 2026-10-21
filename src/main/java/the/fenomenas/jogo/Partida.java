package the.fenomenas.jogo;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Estado de uma partida, guardado na sessão HTTP.
 */
public class Partida implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Quantos rerolls o jogador tem no modo amador. */
    public static final int REROLLS_AMADOR = 2;

    public record Escolha(String cientista, int valor) implements Serializable {
    }

    private Integer codArea;
    private String nomeCarreira;
    private Dificuldade dificuldade = Dificuldade.AMADOR;
    private int rerollsRestantes = REROLLS_AMADOR;
    private final List<Integer> todas = new ArrayList<>();
    private final Deque<Integer> fila = new ArrayDeque<>();
    private Integer codMulherAtual;
    private final Map<Atributo, Escolha> escolhas = new EnumMap<>(Atributo.class);

    /** Fixa área e nome da carreira e prepara o sorteio das cientistas. */
    public void definir(Integer codArea, String nomeCarreira, Dificuldade dificuldade, List<Integer> codigosMulheres) {
        this.codArea = codArea;
        this.dificuldade = dificuldade;
        this.rerollsRestantes = dificuldade == Dificuldade.AMADOR ? REROLLS_AMADOR : 0;
        this.nomeCarreira = nomeCarreira;
        this.todas.clear();
        this.todas.addAll(codigosMulheres);
        this.fila.clear();
        this.escolhas.clear();
        this.codMulherAtual = null;
    }

    /** Sorteia a próxima cientista, sem repetir até todas terem aparecido. */
    public void sortear() {
        if (fila.isEmpty()) {
            List<Integer> embaralhada = new ArrayList<>(todas);
            Collections.shuffle(embaralhada);
            if (embaralhada.size() > 1 && embaralhada.get(0).equals(codMulherAtual)) {
                Collections.swap(embaralhada, 0, embaralhada.size() - 1);
            }
            fila.addAll(embaralhada);
        }
        codMulherAtual = fila.poll();
    }

    public void registrar(Atributo atributo, String cientista, int valor) {
        escolhas.put(atributo, new Escolha(cientista, valor));
    }

    /** Sorteia a próxima cientista, se ainda houver slots a preencher. */
    public void seguir() {
        if (!completa()) {
            sortear();
        }
    }

    /** Troca a cientista atual por outra (só no modo amador, com rerolls restantes). */
    public boolean rerolar() {
        if (dificuldade != Dificuldade.AMADOR || rerollsRestantes <= 0 || completa()) {
            return false;
        }
        rerollsRestantes--;
        sortear();
        return true;
    }

    public boolean jaEscolhido(Atributo atributo) {
        return escolhas.containsKey(atributo);
    }

    public boolean iniciada() {
        return codArea != null && codMulherAtual != null;
    }

    public boolean completa() {
        return escolhas.size() == Atributo.values().length;
    }

    public Integer getCodArea() {
        return codArea;
    }

    public int getRerollsRestantes() {
        return rerollsRestantes;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public String getNomeCarreira() {
        return nomeCarreira;
    }

    public Integer getCodMulherAtual() {
        return codMulherAtual;
    }

    public Map<Atributo, Escolha> getEscolhas() {
        return escolhas;
    }
}
