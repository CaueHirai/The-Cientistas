package the.fenomenas.jogo;

import java.util.function.Function;
import the.fenomenas.model.AreaConhecimento;
import the.fenomenas.model.Mulher;

/**
 * Os seis atributos que o jogador pode "roubar" de cada cientista.
 */
public enum Atributo {
    MEMORIA("Memória", Mulher::getMemoria, AreaConhecimento::getPesoMemoria),
    ANALISE("Análise", Mulher::getAnalise, AreaConhecimento::getPesoAnalise),
    CRIATIVIDADE("Criatividade", Mulher::getCriatividade, AreaConhecimento::getPesoCriatividade),
    RIGOR("Rigor", Mulher::getRigor, AreaConhecimento::getPesoRigor),
    RACIOCINIO("Raciocínio", Mulher::getRaciocinio, AreaConhecimento::getPesoRaciocinio),
    CALCULO("Cálculo", Mulher::getCalculo, AreaConhecimento::getPesoCalculo);

    private final String rotulo;
    private final Function<Mulher, Integer> valor;
    private final Function<AreaConhecimento, Integer> peso;

    Atributo(String rotulo, Function<Mulher, Integer> valor, Function<AreaConhecimento, Integer> peso) {
        this.rotulo = rotulo;
        this.valor = valor;
        this.peso = peso;
    }

    public String getRotulo() {
        return rotulo;
    }

    public int valorDe(Mulher m) {
        Integer v = valor.apply(m);
        return v == null ? 0 : v;
    }

    public int pesoDe(AreaConhecimento a) {
        Integer p = peso.apply(a);
        return p == null ? 0 : p;
    }

    /** Converte o texto do input radio; devolve null se for inválido. */
    public static Atributo de(String texto) {
        try {
            return valueOf(texto);
        } catch (IllegalArgumentException | NullPointerException ex) {
            return null;
        }
    }
}
