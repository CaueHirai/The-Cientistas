package the.fenomenas.jogo;

/**
 * Modos de jogo. No amador o jogador vê o valor de cada atributo antes de
 * roubá-lo; no profissional os valores ficam escondidos até o resultado.
 */
public enum Dificuldade {
    AMADOR("Amador", "Você vê o valor de cada atributo antes de roubá-lo.", true),
    PROFISSIONAL("Profissional", "Os valores ficam escondidos. Confie no que você sabe sobre cada cientista.", false);

    private final String rotulo;
    private final String descricao;
    private final boolean mostraValores;

    Dificuldade(String rotulo, String descricao, boolean mostraValores) {
        this.rotulo = rotulo;
        this.descricao = descricao;
        this.mostraValores = mostraValores;
    }

    public String getRotulo() {
        return rotulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isMostraValores() {
        return mostraValores;
    }

    /** Converte o texto do input radio; se for inválido, cai no modo amador. */
    public static Dificuldade de(String texto) {
        try {
            return valueOf(texto);
        } catch (IllegalArgumentException | NullPointerException ex) {
            return AMADOR;
        }
    }
}
